package com.symulakr.dinstar.smsserver.message.body;

public class SimpleResponseBody extends ResponseBody
{

   public static final int BODY_LENGTH = 1;
   private boolean succeed;

   @Override
   protected byte[] createBody()
   {
      byte[] bytes = new byte[BODY_LENGTH];
      bytes[0] = (byte) (succeed ? 0 : 1);
      return bytes;
   }

   public SimpleResponseBody(boolean succeed)
   {
      this.succeed = succeed;
   }

}
