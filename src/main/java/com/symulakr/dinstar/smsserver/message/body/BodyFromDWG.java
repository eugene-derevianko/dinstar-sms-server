package com.symulakr.dinstar.smsserver.message.body;

import com.symulakr.dinstar.smsserver.common.ToBytes;

public abstract class BodyFromDWG
{

   public BodyFromDWG(ToBytes body)
   {
      parseByteArray(body.toBytes());
   }

   protected abstract void parseByteArray(byte[] body);
}
