package com.symulakr.dinstar.smsserver.message;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class Head
{

   private byte[] bytes = new byte[24];

   private int length;
   private MessageId messageId;
   private int messageType;
   private byte[] flag = new byte[2];

   public Head(byte[] bytes)
   {
      this.bytes = bytes;
      this.length = ByteBuffer.wrap(Arrays.copyOf(bytes, 4))
            .getInt();
      this.messageId = new MessageId(Arrays.copyOfRange(bytes, 4, 20));
      this.messageType = ByteBuffer.wrap(Arrays.copyOfRange(bytes, 20, 22))
            .getShort();
      this.flag = Arrays.copyOfRange(bytes, 22, 24);
   }

   public byte[] getBytes()
   {
      return bytes;
   }

   public int getLength()
   {
      return length;
   }

   public MessageId getMessageId()
   {
      return messageId;
   }

   public int getMessageType()
   {
      return messageType;
   }

   public byte[] getFlag()
   {
      return flag;
   }
}
