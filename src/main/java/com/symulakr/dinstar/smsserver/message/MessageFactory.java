package com.symulakr.dinstar.smsserver.message;

import java.io.BufferedInputStream;
import java.io.IOException;

public class MessageFactory
{

   public static final int HEAD_LENGTH = 24;

   public AbstractMessage getMessage(BufferedInputStream stream) throws IOException
   {
      while (stream.available() < HEAD_LENGTH)
      {

      }
      System.out.println("Message Head: ");
      byte[] messageHead = new byte[HEAD_LENGTH];
      stream.read(messageHead);
      Head head = new Head(messageHead);
      AbstractMessage message = createMessage(head);
      message.readBody(stream);
      return message;
   }

   private AbstractMessage createMessage(Head head)
   {
      switch (head.getMessageType())
      {
         case AuthenticationRequest.TYPE:
            return new AuthenticationRequest(head);
         default:
            return new DefaultMessage(head);

      }
   }

}
