package com.symulakr.dinstar.smsserver.message.ussd;

import com.symulakr.dinstar.smsserver.message.enums.AsByte;

public enum UssdStatus implements AsByte
{

   NFUAR(0, "No further user action required"),
   FUAR(1, "Further user action required"),
   TBN(2, "USSD terminated by network"),
   ONS(4, "Operation not supported");

   private int code;
   private String description;

   UssdStatus(int code, String description)
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
