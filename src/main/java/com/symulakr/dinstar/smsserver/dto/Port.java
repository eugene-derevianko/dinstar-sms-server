package com.symulakr.dinstar.smsserver.dto;

import com.symulakr.dinstar.smsserver.common.ToBytes;
import com.symulakr.dinstar.smsserver.utils.ArrayUtils;

public class Port implements ToBytes
{

   private String number;
   private byte port;

   public Port(byte port)
   {
      this.port = port;
   }

   public String getNumber()
   {
      return number;
   }

   public void setNumber(String number)
   {
      this.number = number;
   }

   public byte getPort()
   {
      return port;
   }

   @Override
   public byte[] toBytes()
   {
      return ArrayUtils.toArray(port);
   }
}
