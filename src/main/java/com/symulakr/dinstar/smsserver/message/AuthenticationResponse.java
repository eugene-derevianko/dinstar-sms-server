package com.symulakr.dinstar.smsserver.message;

public class AuthenticationResponse extends SimpleResponse
{

   public AuthenticationResponse(MessageId messageId, boolean isSucceed)
   {
      super(messageId, isSucceed);
   }

   @Override
   public int getType()
   {
      return 0x10;
   }
}
