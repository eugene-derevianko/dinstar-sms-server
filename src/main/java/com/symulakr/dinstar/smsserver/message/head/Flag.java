package com.symulakr.dinstar.smsserver.message.head;

import com.symulakr.dinstar.smsserver.common.ToBytes;

public class Flag implements ToBytes
{

   private byte[] bytes;

   public Flag(byte[] bytes)
   {
      this.bytes = bytes;
   }

   public Flag()
   {
      this(new byte[]{0, 0});
   }

   @Override
   public byte[] toBytes()
   {
      return bytes;
   }
}
