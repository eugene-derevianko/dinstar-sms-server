package com.symulakr.dinstar.smsserver;

import com.symulakr.dinstar.smsserver.message.AuthenticationRequest;
import com.symulakr.dinstar.smsserver.message.AuthenticationResponse;
import com.symulakr.dinstar.smsserver.message.Head;
import com.symulakr.dinstar.smsserver.message.MessageType;

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

      final int HEAD_LENGTH = 24;
      byte[] messageHead = new byte[HEAD_LENGTH];

      try
      {

         Socket connectionSocket = welcomeSocket.accept();

         System.out.println("Accept");
         BufferedInputStream reader = new BufferedInputStream(connectionSocket.getInputStream());

         while (!application.isStopped())
         {

            while (reader.available() < HEAD_LENGTH)
            {

            }
            System.out.println("Message Head: ");
            reader.read(messageHead);
            Head head = new Head(messageHead);
            print(head.getBytes());

            System.out.println("Length of body is: " + head.getLength());
            System.out.println("Message Id: ");
            System.out.println("\tMAC address is: " + head.getMessageId().getMacAddress());
            System.out.println("\tTime is: " + head.getMessageId().getMessageTime());
            System.out.println("\tSerial number is: " + head.getMessageId().getSerialNo());
            System.out.println("Message type is: " + head.getMessageType());




            /*
             * for (int i = 0; i < HEAD_LENGTH; i++) { System.out.printf("%02x ", reader.read()); }
             */

            // if (reader.available() > 0)
            // {
            // byte[] array = new byte[reader.available()];
            //
            // System.out.println(reader.read(array));
            // for (byte b : array)
            // {
            // System.out.print(b);
            // }
            //
            // }

            while (reader.available() < head.getLength())
            {

            }
            System.out.println("Message body: ");

            byte[] messageBody = new byte[head.getLength()];

            reader.read(messageBody);

            print(messageBody);

            if (head.getMessageType() == MessageType.X0F)
            {
               AuthenticationRequest authenticationRequest = new AuthenticationRequest(head.getMessageId());
               authenticationRequest.setBody(messageBody);
               System.out.println("UserId:'" + authenticationRequest.getUserId() + "'");
               System.out.println("Password:'" + authenticationRequest.getPassword() +"'");

               AuthenticationResponse authenticationResponse = new AuthenticationResponse(head.getMessageId(), true);
               System.out.println("Response Message: ");
               print(authenticationResponse.toBytes());

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

   private void print(byte[] array)
   {
      StringBuilder sb = new StringBuilder();
      String delimiter = "";
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
