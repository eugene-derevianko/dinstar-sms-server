package com.symulakr.dinstar.smsserver.message.body;

import com.symulakr.dinstar.smsserver.common.ToBytes;

public abstract class ResponseBody implements ToBytes
{

   private byte[] bytes;

   protected abstract byte[] createBody();

   public int getLength()
   {
      return toBytes().length;
   }

   @Override
   public byte[] toBytes()
   {
      if (bytes == null)
      {
         bytes = createBody();
      }
      return bytes;
   }
}
