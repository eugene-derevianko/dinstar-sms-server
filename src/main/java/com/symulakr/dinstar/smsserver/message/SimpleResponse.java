package com.symulakr.dinstar.smsserver.message;

public abstract class SimpleResponse extends AbstractMessage
{

   public static final int BODY_LENGTH = 1;

   private byte[] body = new byte[BODY_LENGTH];

   public SimpleResponse(MessageId messageId, boolean isSucceed)
   {
      super(messageId);
      body[0] = (byte) (isSucceed ? 0 : 1);
   }

   @Override
   public byte[] getBody()
   {
      return body;
   }
}
