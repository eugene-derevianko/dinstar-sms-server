package com.symulakr.dinstar.smsserver.message;

import java.nio.ByteBuffer;
import java.util.Arrays;

import com.symulakr.dinstar.smsserver.utils.ArrayUtils;

@Deprecated
public class Head
{

   public static final int HEAD_LENGTH = 24;

   private byte[] bytes = new byte[HEAD_LENGTH];

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

   @Override
   public final String toString()
   {
      return new StringBuilder().append(ArrayUtils.toString(bytes))
            .append("\nLength: ")
            .append(length)
            .append("\nMessage Id: \n")
            .append(messageId)
            .append("\nType: ")
            .append(String.format("%04x", messageType))
            .append("\nFlag: ")
            .append(ArrayUtils.toString(flag))
            .toString();
   }

}
