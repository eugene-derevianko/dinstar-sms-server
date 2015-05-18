package com.symulakr.dinstar.smsserver.message;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;

public abstract class AbstractMessage implements Message
{

   private Head head;
   protected byte[] body;

   public AbstractMessage(Head head)
   {
      this.head = head;
   }

   public Head getHead()
   {
      return head;
   }

   public byte[] getBody()
   {
      return body;
   }

   public void readBody(BufferedInputStream stream) throws IOException
   {
      while (stream.available() < getLength())
      {

      }
      this.body = new byte[getLength()];
      stream.read(this.body);
      parseBody();
   }

   protected abstract void parseBody();

   public byte[] getId()
   {
      return getMessageId().toBytes();
   }

   public MessageId getMessageId()
   {
      return getHead().getMessageId();
   }

   public byte[] getFlag()
   {
      return getHead().getFlag();
   }

   public int getLength()
   {
      return getHead().getLength();
   }

   public abstract int getType();

   public byte[] toBytes()
   {
      return ByteBuffer.allocate(24 + getLength())
            .putInt(getLength())
            .put(getId())
            .putShort((short) getType())
            .put(getFlag())
            .put(getBody())
            .array();
   }

   @Override
   public String toString()
   {
      return "Message:\n" +
            "Length: " + getLength() +
            "\n" + getMessageId() +
            "Type: " + getType() +
            ", flag=" + Arrays.toString(getFlag()) +
            ", body=" + Arrays.toString(body);
   }

}
