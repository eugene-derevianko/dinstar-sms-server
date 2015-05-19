package com.symulakr.dinstar.smsserver.message.service;

import com.symulakr.dinstar.smsserver.message.IncomingMessage;
import com.symulakr.dinstar.smsserver.message.OutgoingMessage;

import java.util.Arrays;

public class AuthenticationRequest extends IncomingMessage
{

   private String userId;
   private String password;

   public AuthenticationRequest(byte[] head)
   {
      this.head = head;
   }

   public String getUserId()
   {
      return userId;
   }

   public String getPassword()
   {
      return password;
   }

   @Override
   protected void parseBody()
   {
      this.userId = new String(Arrays.copyOfRange(body, 0, 16));
      this.password = new String(Arrays.copyOfRange(body, 16, 32));
   }

   @Override
   public OutgoingMessage createResponse()
   {
      return new AuthenticationResponse(getMessageId(), true);
   }

   @Override
   public String toString()
   {
      return new StringBuilder(super.toString())
            .append("\n\tuserId: ")
            .append(userId)
            .append("\n\tpassword: ")
            .append(password)
            .toString();
   }

}
