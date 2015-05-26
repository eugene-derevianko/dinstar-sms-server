package com.symulakr.dinstar.smsserver.message.head;

import java.nio.ByteBuffer;
import java.util.Arrays;

import com.symulakr.dinstar.smsserver.common.ToBytes;

public class Head implements ToBytes
{

   public static final int LENGTH = 24;
   public static final int BODY_LENGTH_INDEX = 0;
   public static final int MESSAGE_ID_INDEX = BODY_LENGTH_INDEX + 4;
   public static final int TYPE_INDEX = MESSAGE_ID_INDEX + MessageId.LENGTH;
   public static final int FLAG_INDEX = TYPE_INDEX + 2;

   private byte[] bytes;
   private int lengthOfBody;
   private MessageId messageId;
   private MsType messageType;
   private Flag flag;

   public Head(byte[] bytes)
   {
      this.bytes = bytes;
      this.lengthOfBody = ByteBuffer.wrap(bytes)
            .getInt(BODY_LENGTH_INDEX);
      this.messageId = new MessageId(Arrays.copyOfRange(bytes, MESSAGE_ID_INDEX, TYPE_INDEX));
      this.messageType = MsType.fromBytes(Arrays.copyOfRange(bytes, TYPE_INDEX, FLAG_INDEX));
      this.flag = new Flag(Arrays.copyOfRange(bytes, FLAG_INDEX, LENGTH));
   }

   public Head(int lengthOfBody, MessageId messageId, MsType messageType, Flag flag)
   {
      this.lengthOfBody = lengthOfBody;
      this.messageId = messageId;
      this.messageType = messageType;
      this.flag = flag;
      bytes = ByteBuffer.allocate(LENGTH)
            .putInt(lengthOfBody)
            .put(messageId.toBytes())
            .put(messageType.toBytes())
            .put(flag.toBytes())
            .array();
   }

   @Override
   public byte[] toBytes()
   {
      return bytes;
   }

   @Override
   public String toString()
   {
      return "Head{" +
            "flag=" + flag +
            ", messageType=" + messageType +
            ", messageId=" + messageId +
            ", lengthOfBody=" + lengthOfBody +
            '}';
   }

   public int getLengthOfBody()
   {
      return lengthOfBody;
   }

   public MessageId getMessageId()
   {
      return messageId;
   }

   public MsType getMessageType()
   {
      return messageType;
   }

   public Flag getFlag()
   {
      return flag;
   }

}
