package com.symulakr.dinstar.smsserver.message.body;

import com.symulakr.dinstar.smsserver.common.ToBytes;

public class Body implements ToBytes
{

   private byte[] bytes;

   public Body(byte[] bytes)
   {
      this.bytes = bytes;
   }

   @Override
   public byte[] toBytes()
   {
      return bytes;
   }

}
