package com.symulakr.dinstar.smsserver.message;

import java.nio.ByteBuffer;

import com.symulakr.dinstar.smsserver.utils.HeadParser;

public abstract class OutgoingMessage extends AbstractMessage
{

   protected abstract void createBody();

   @Override
   public byte[] getFlag()
   {
      return ByteBuffer.allocate(2)
            .array();
   }

   protected void createHead(byte[] messageId)
   {
      head = ByteBuffer.allocate(HeadParser.HEAD_LENGTH)
            .putInt(getLength())
            .put(messageId)
            .putShort(getType())
            .put(getFlag())
            .array();
   }

   @Override
   public String toString()
   {
      return new StringBuilder("\n===============================================\n")
            .append("Outgoing Message:")
            .append(super.toString())
            .toString();
   }

}
