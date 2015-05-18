package com.symulakr.dinstar.smsserver.message;

public abstract class SimpleResponse extends AbstractMessage
{

   public static final int BODY_LENGTH = 1;

   @Override
   protected void parseBody()
   {

   }

   @Override
   public int getLength()
   {
      return BODY_LENGTH;
   }

   public SimpleResponse(Head head, boolean isSucceed)
   {
      super(head);
      body = new byte[getLength()];
      body[0] = (byte) (isSucceed ? 0 : 1);
   }

}
