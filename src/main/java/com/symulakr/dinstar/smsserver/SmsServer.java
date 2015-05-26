package com.symulakr.dinstar.smsserver;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.symulakr.dinstar.smsserver.message.Body;
import com.symulakr.dinstar.smsserver.message.IncomingMessage;
import com.symulakr.dinstar.smsserver.message.Message;
import com.symulakr.dinstar.smsserver.message.MessageFactory;
import com.symulakr.dinstar.smsserver.message.OutgoingMessage;
import com.symulakr.dinstar.smsserver.message.head.Head;
import com.symulakr.dinstar.smsserver.utils.HeadParser;

@Component
public class SmsServer extends Thread
{

   @Autowired
   private ConnectionSocket connectionSocket;
   @Autowired
   private IncomingQueue incomingQueue;

   private final static Logger LOG = LogManager.getLogger(SmsServer.class);

   @Value("${sms.server.port}")
   private int port;

   private boolean started = false;

   @Override
   public void run()
   {
      try
      {
         MessageFactory messageFactory = new MessageFactory();
         BufferedInputStream stream = new BufferedInputStream(connectionSocket.getInputStream());
         byte[] bytes = new byte[HeadParser.HEAD_LENGTH];

         while (started)
         {
            if (stream.read(bytes) == HeadParser.HEAD_LENGTH)
            {
               Head head = new Head(bytes);

               IncomingMessage message = messageFactory.createMessage(bytes);
               byte[] body = new byte[head.getLengthOfBody()];
               if (stream.read(body) == head.getLengthOfBody())
               {
                  message.setBody(body);
                  incomingQueue.offer(new Message(head, new Body(body)));
               }

               LOG.info(incomingQueue.size());

               OutgoingMessage outgoingMessage = message.createResponse();
               if (outgoingMessage != null)
               {
                  LOG.info(outgoingMessage);
                  ByteArrayOutputStream outToClient = new ByteArrayOutputStream();
                  outToClient.write(outgoingMessage.toBytes());
                  outToClient.writeTo(connectionSocket.getOutputStream());
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
