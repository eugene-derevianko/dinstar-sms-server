package com.symulakr.dinstar.smsserver.message.enums;

public enum ContentType implements AsByte
{

   SMS(0),
   MMS(1);

   private final int code;

   ContentType(int code)
   {
      this.code = code;
   }

   @Override
   public byte asByte()
   {
      return (byte) code;
   }
}
