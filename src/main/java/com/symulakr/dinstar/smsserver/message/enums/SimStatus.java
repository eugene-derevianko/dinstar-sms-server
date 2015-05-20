package com.symulakr.dinstar.smsserver.message.enums;

public enum SimStatus implements AsByte
{
   WORKS(0),
   NO_SIM(1),
   NOT_REGISTRED(2),
   UNAVAILABLE(3);

   private int code;

   SimStatus(int code)
   {
      this.code = code;
   }

   @Override
   public byte asByte()
   {
      return (byte) code;
   }
}