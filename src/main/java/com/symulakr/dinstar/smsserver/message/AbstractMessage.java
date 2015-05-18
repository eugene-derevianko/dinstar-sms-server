package com.symulakr.dinstar.smsserver.message;

import java.nio.ByteBuffer;

public abstract class AbstractMessage implements Message
{

   protected byte[] id = new byte[16];
   protected byte[] flag = new byte[2];

   public AbstractMessage(MessageId messageId)
   {
      this.id = messageId.toBytes();
   }

   public abstract byte[] getBody();

   public abstract int getType();

   public byte[] toBytes()
   {
      return ByteBuffer.allocate(24 + getBody().length)
            .putInt(getBody().length)
            .put(id)
            .putShort((short) getType())
            .put(flag)
            .put(getBody())
            .array();
   }
}
