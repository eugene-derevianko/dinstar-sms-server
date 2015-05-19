package com.symulakr.dinstar.smsserver.message;

public abstract class SimpleResponse extends OutgoingMessage
{

   public static final int BODY_LENGTH = 1;

   private boolean succeed;

   @Override
   public int getLength()
   {
      return BODY_LENGTH;
   }

   @Override
   protected void createBody()
   {
      body = new byte[getLength()];
      body[0] = (byte) (succeed ? 0 : 1);
   }

   public SimpleResponse(byte[] messageId, boolean succeed)
   {
      this.succeed = succeed;
      createHead(messageId);
      createBody();
   }

}
