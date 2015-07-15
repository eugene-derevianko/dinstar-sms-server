package com.symulakr.dinstar.smsserver;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.symulakr.dinstar.smsserver.handlers.Handler;
import com.symulakr.dinstar.smsserver.message.Message;
import com.symulakr.dinstar.smsserver.message.body.Body;
import com.symulakr.dinstar.smsserver.message.head.Head;
import com.symulakr.dinstar.smsserver.utils.HeadParser;

@Service
public class SmsServer extends Thread
{

   // @Autowired
   // private ConnectionSocket connectionSocket;

   @Autowired
   private HandlerFactory handlerFactory;
   // @Autowired
   // private MessageSender messageSender;

   private final static Logger LOG = LogManager.getLogger(SmsServer.class);

   @Value("${sms.server.port}")
   private int port;

   private boolean started = false;

   private Map<SocketChannel, List<byte[]>> keepDataTrack = new HashMap<>();
   private ByteBuffer buffer = ByteBuffer.allocate(2 * 1024);

   // public SmsServer()
   // {
   // }
   //
   // public SmsServer(HandlerFactory handlerFactory, int port)
   // {
   // this.handlerFactory = handlerFactory;
   // this.port = port;
   // }

   @Override
   public void run()
   {
      // try
      // {
      // BufferedInputStream stream = new BufferedInputStream(connectionSocket.getInputStream());
      // byte[] bytes = new byte[HeadParser.HEAD_LENGTH];

      try (Selector selector = Selector.open();
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open())
      {
         if ((serverSocketChannel.isOpen()) && (selector.isOpen()))
         {
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.setOption(StandardSocketOptions.SO_RCVBUF, 256 * 1024);
            serverSocketChannel.setOption(StandardSocketOptions.SO_REUSEADDR, true);
            serverSocketChannel.bind(new InetSocketAddress(port));

            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            LOG.info("Waiting for connections ...");

            while (started)
            {
               int count = selector.select();
               LOG.info("{} keys whose ready-operation sets were updated", count);

               Iterator keys = selector.selectedKeys()
                     .iterator();

               while (keys.hasNext())
               {
                  SelectionKey key = (SelectionKey) keys.next();
                  keys.remove();
                  if (!key.isValid())
                  {
                     continue;
                  }
                  if (key.isAcceptable())
                  {
                     acceptOP(key, selector);
                  }
                  else if (key.isReadable())
                  {
                     this.readOP(key);
                  }
                  else if (key.isWritable())
                  {
                     this.writeOP(key);
                  }
               }
            }
         }
         else
         {
            LOG.error("The server socket channel or selector cannot be opened!");
         }

         // }
         // catch (IOException e)
         // {
         // e.printStackTrace();
         // }
         //
         // if (stream.read(bytes) == HeadParser.HEAD_LENGTH)
         // {
         // Head head = new Head(bytes);
         // byte[] body = new byte[head.getLengthOfBody()];
         // if (stream.read(body) == head.getLengthOfBody())
         // {
         // Message message = new Message(head, new Body(body));
         // Handler handler = handlerFactory.getHandler(head.getMessageType());
         // Message outgoingMessage = handler.processMessage(message);
         // if (outgoingMessage != null)
         // {
         // messageSender.sendMessage(outgoingMessage);
         // }
         // }
         // }

      }
      catch (IOException ex)
      {
         LOG.error(ex);
      }
   }

   // isAcceptable returned true
   private void acceptOP(SelectionKey key, Selector selector) throws IOException
   {
      ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
      SocketChannel socketChannel = serverChannel.accept();
      socketChannel.configureBlocking(false);
      LOG.info("Incoming connection from: " + socketChannel.getRemoteAddress());
      // write a welcome message
      // socketChannel.write(ByteBuffer.wrap("Hello!\n".getBytes("UTF-8")));
      // register channel with selector for further I/O
      keepDataTrack.put(socketChannel, new ArrayList<>());
      socketChannel.register(selector, SelectionKey.OP_READ);
   }

   // isReadable returned true
   private void readOP(SelectionKey key)
   {
      try
      {
         SocketChannel socketChannel = (SocketChannel) key.channel();
         buffer.clear();
         int numRead = 0;
         try
         {
            numRead = socketChannel.read(buffer);
         }
         catch (IOException e)
         {
            LOG.error("Cannot read error!");
         }
         if (numRead == -1)
         {
            this.keepDataTrack.remove(socketChannel);
            LOG.info("Connection closed by: " + socketChannel.getRemoteAddress());
            socketChannel.close();
            key.cancel();
            return;
         }
         buffer.flip();
         byte[] bytes = new byte[HeadParser.HEAD_LENGTH];
         buffer.get(bytes);
         Head head = new Head(bytes);
         byte[] body = new byte[head.getLengthOfBody()];
         buffer.get(body);
         Message message = new Message(head, new Body(body));
         LOG.info("Receive message\n{}", message);

         Handler handler = handlerFactory.getHandler(head.getMessageType());
         Message outgoingMessage = handler.processMessage(message);
         if (outgoingMessage != null)
         {
            LOG.info("Send message\n{}", outgoingMessage);
            doEchoJob(key, outgoingMessage.toBytes());
         }

      }
      catch (IOException ex)
      {
         LOG.error(ex);
      }
   }

   private void writeOP(SelectionKey key) throws IOException
   {
      SocketChannel socketChannel = (SocketChannel) key.channel();
      List<byte[]> channelData = keepDataTrack.get(socketChannel);
      Iterator<byte[]> its = channelData.iterator();
      while (its.hasNext())
      {
         byte[] it = its.next();
         its.remove();
         socketChannel.write(ByteBuffer.wrap(it));
      }
      key.interestOps(SelectionKey.OP_READ);
   }

   private void doEchoJob(SelectionKey key, byte[] data)
   {
      SocketChannel socketChannel = (SocketChannel) key.channel();
      List<byte[]> channelData = keepDataTrack.get(socketChannel);
      channelData.add(data);
      key.interestOps(SelectionKey.OP_WRITE);
   }

   @PreDestroy
   public void onStop() throws IOException
   {
      started = false;
      LOG.info("PreDestroy");
   }

   @PostConstruct
   public void onStart()
   {
      started = true;
      start();
   }

   public void setHandlerFactory(HandlerFactory handlerFactory)
   {
      this.handlerFactory = handlerFactory;
   }

   public void setPort(int port)
   {
      this.port = port;
   }
}
