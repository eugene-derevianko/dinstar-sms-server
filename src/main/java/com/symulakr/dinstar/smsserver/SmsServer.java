package com.symulakr.dinstar.smsserver;

import com.symulakr.dinstar.smsserver.message.*;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

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

         while (!application.isStopped())
         {

            AbstractMessage message = messageFactory.getMessage(stream);
            print(message);
            if (message instanceof AuthenticationRequest)
            {

               System.out.println("UserId:'" + ((AuthenticationRequest) message).getUserId() + "'");
               System.out.println("Password:'" + ((AuthenticationRequest) message).getPassword() + "'");

               AuthenticationResponse authenticationResponse = new AuthenticationResponse(message.getHead(), true);
               System.out.println("Response Message: ");
               System.out.println(authenticationResponse);
               print(authenticationResponse);
               ByteArrayOutputStream outToClient = new ByteArrayOutputStream();
               outToClient.write(authenticationResponse.toBytes());
               outToClient.writeTo(connectionSocket.getOutputStream());
            }

            System.out.println("---------------------------------------------------------");

         }
         connectionSocket.close();
         System.out.println("End");

      }
      catch (IOException ex)
      {
         System.out.println(ex.toString());
      }

   }

   private void print(Message message)
   {
      StringBuilder sb = new StringBuilder();
      String delimiter = "";
      byte[] array = message.toBytes();
      for (int i = 0; i < array.length; i++)
      {
         if (i != 0 && i % 16 == 0)
         {
            delimiter = "\n";
         }
         sb.append(delimiter)
               .append(String.format("%02x", array[i]));
         delimiter = " ";

      }
      sb.append('\n');

      System.out.println(sb);
   }

}
