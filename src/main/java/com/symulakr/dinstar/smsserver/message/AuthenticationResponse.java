package com.symulakr.dinstar.smsserver.message;

public class AuthenticationResponse extends SimpleResponse
{

   public static final int TYPE = 0x10;

   public AuthenticationResponse(Head head, boolean isSucceed)
   {
      super(head, isSucceed);
   }

   @Override
   public int getType()
   {
      return TYPE;
   }
}
