package com.symulakr.dinstar.smsserver;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.symulakr.dinstar.smsserver.message.IncomingMessage;
import com.symulakr.dinstar.smsserver.message.MessageFactory;
import com.symulakr.dinstar.smsserver.message.OutgoingMessage;
import com.symulakr.dinstar.smsserver.utils.HeadParser;

@Component
public class SmsServer extends Thread
{

   private final static Logger LOG = LogManager.getLogger(SmsServer.class);

   @Value("${sms.server.port}")
   private int port;

   private boolean started = false;

   @Override
   public void run()
   {
      try
      {
         ServerSocket welcomeSocket = new ServerSocket(port);
         Socket connectionSocket = welcomeSocket.accept();
         MessageFactory messageFactory = new MessageFactory();
         LOG.info("Accept");
         BufferedInputStream stream = new BufferedInputStream(connectionSocket.getInputStream());
         byte[] head = new byte[HeadParser.HEAD_LENGTH];

         while (started)
         {
            if (stream.read(head) == HeadParser.HEAD_LENGTH)
            {
               IncomingMessage message = messageFactory.createMessage(head);
               byte[] body = new byte[message.getLength()];
               if (stream.read(body) == message.getLength())
               {
                  message.setBody(body);
               }

               LOG.info(message);

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
         connectionSocket.close();
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
