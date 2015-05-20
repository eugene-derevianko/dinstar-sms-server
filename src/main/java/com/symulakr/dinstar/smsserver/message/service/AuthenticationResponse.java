package com.symulakr.dinstar.smsserver.message.service;

import com.symulakr.dinstar.smsserver.message.enums.OutgoingMessageType;
import com.symulakr.dinstar.smsserver.message.SimpleResponse;

public class AuthenticationResponse extends SimpleResponse
{

   public AuthenticationResponse(byte[] messageId, boolean succeed)
   {
      super(messageId, succeed);
   }

   @Override
   public short getType()
   {
      return OutgoingMessageType.X10.getType();
   }

}
