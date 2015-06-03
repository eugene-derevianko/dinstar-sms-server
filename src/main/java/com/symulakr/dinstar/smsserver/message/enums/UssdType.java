package com.symulakr.dinstar.smsserver.message.enums;

public enum UssdType implements AsByte
{

   SEND(1, "Send"),
   END_SESSION(2, "End session");

   private int code;
   private String description;

   UssdType(int code, String description)
   {
      this.code = code;
      this.description = description;
   }

   @Override
   public byte asByte()
   {
      return (byte) code;
   }

   public String getDescription()
   {
      return description;
   }
}
