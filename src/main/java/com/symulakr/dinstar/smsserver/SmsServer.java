package com.symulakr.dinstar.smsserver;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.symulakr.dinstar.smsserver.handlers.Handler;
import com.symulakr.dinstar.smsserver.message.Message;
import com.symulakr.dinstar.smsserver.message.body.Body;
import com.symulakr.dinstar.smsserver.message.head.Head;
import com.symulakr.dinstar.smsserver.utils.HeadParser;

@Component
public class SmsServer extends Thread
{

   @Autowired
   private HandlerFactory handlerFactory;
   @Autowired
   private SocketChannel socketChannel;

   private final static Logger LOG = LogManager.getLogger(SmsServer.class);

   @Value("${sms.server.port}")
   private int port;

   private boolean started = false;

   @Override
   public void run()
   {
      try
      {
         ByteBuffer byteBuffer = ByteBuffer.allocate(HeadParser.HEAD_LENGTH);
         while (started)
         {
            if (socketChannel.read(byteBuffer) == HeadParser.HEAD_LENGTH)
            {
               Head head = new Head(byteBuffer.array());
               byteBuffer = ByteBuffer.allocate(head.getLengthOfBody());
               if (socketChannel.read(byteBuffer) == head.getLengthOfBody())
               {
                  Message message = new Message(head, new Body(byteBuffer.array()));
                  Handler handler = handlerFactory.getHandler(head.getMessageType());
                  Message outgoingMessage = handler.processMessage(message);
                  if (outgoingMessage != null)
                  {
                     socketChannel.write(ByteBuffer.wrap(outgoingMessage.toBytes()));
                  }
               }
            }
         }
      }
      catch (IOException ex)
      {
         LOG.error(ex);
      }
   }

   @PreDestroy
   public void onStop() throws IOException
   {
      socketChannel.close();
      started = false;
      LOG.info("PreDestroy");
   }

   @PostConstruct
   public void onStart()
   {
      started = true;
      start();
   }

}
