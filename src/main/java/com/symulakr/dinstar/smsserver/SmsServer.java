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
import com.symulakr.dinstar.smsserver.utils.Logger;

public class SmsServer extends Thread
{

   private Application application;
   private ServerSocket welcomeSocket;

   public SmsServer(Application application) throws IOException
   {
      this.application = application;
      this.welcomeSocket = new ServerSocket(6789);
   }

   @Override
   public synchronized void start()
   {
      application.setStopped(false);
      super.start();
   }

   @Override
   public void run()
   {
      try
      {
         Socket connectionSocket = welcomeSocket.accept();
         MessageFactory messageFactory = new MessageFactory();
         System.out.println("Accept");
         BufferedInputStream stream = new BufferedInputStream(connectionSocket.getInputStream());
         byte[] head = new byte[HeadParser.HEAD_LENGTH];

         while (!application.isStopped())
         {
            if (stream.read(head) == HeadParser.HEAD_LENGTH)
            {
               IncomingMessage message = messageFactory.createMessage(head);
               byte[] body = new byte[message.getLength()];
               if (stream.read(body) == message.getLength())
               {
                  message.setBody(body);
               }

               Logger.sout(message);

               OutgoingMessage outgoingMessage = message.createResponse();
               if (outgoingMessage != null)
               {
                  Logger.sout(outgoingMessage);
                  ByteArrayOutputStream outToClient = new ByteArrayOutputStream();
                  outToClient.write(outgoingMessage.toBytes());
                  outToClient.writeTo(connectionSocket.getOutputStream());
               }
            }
         }
         connectionSocket.close();
         System.out.println("End");
      }
      catch (IOException ex)
      {
         System.out.println(ex.toString());
      }
   }

}
