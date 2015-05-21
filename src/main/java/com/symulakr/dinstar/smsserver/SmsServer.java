package com.symulakr.dinstar.smsserver;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.symulakr.dinstar.smsserver.message.IncomingMessage;
import com.symulakr.dinstar.smsserver.message.MessageFactory;
import com.symulakr.dinstar.smsserver.message.OutgoingMessage;
import com.symulakr.dinstar.smsserver.utils.HeadParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

@Component
public class SmsServer extends Thread
{

   private final static Logger LOG = LogManager.getLogger(SmsServer.class);

   private ServerSocket welcomeSocket;
   private Socket connectionSocket;

   @Value("${sms.server.port}")
   private int port;

   public SmsServer() throws IOException
   {
      this.welcomeSocket = new ServerSocket(port);
   }

   @Override
   public void run()
   {
      try
      {
         connectionSocket = welcomeSocket.accept();
         MessageFactory messageFactory = new MessageFactory();
         LOG.info("Accept");
         BufferedInputStream stream = new BufferedInputStream(connectionSocket.getInputStream());
         byte[] head = new byte[HeadParser.HEAD_LENGTH];

         while (true)
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
      }
      catch (IOException ex)
      {
         LOG.error(ex);
      }

   }

   @PreDestroy
   public void onStop() throws IOException
   {
      LOG.info("End");
      if (connectionSocket != null)
      {
         connectionSocket.close();
      }
   }

}
