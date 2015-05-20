package com.symulakr.dinstar.smsserver.utils;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Date;

import com.symulakr.dinstar.smsserver.message.enums.IncomingMessageType;

public abstract class HeadParser
{

   public static final int HEAD_LENGTH = 24;
   public static final int LENGTH_INDEX = 0;
   public static final int MESSAGE_ID_INDEX = LENGTH_INDEX + 4;

   // Message Id --------------------------------------------------
   public static final int MAC_ADDRESS_INDEX = MESSAGE_ID_INDEX;
   public static final int MAC_ADDRESS_END = MAC_ADDRESS_INDEX + 6;
   public static final int TIME_INDEX = MAC_ADDRESS_INDEX + 8;
   public static final int SERIAL_NO_INDEX = TIME_INDEX + 4;
   // -------------------------------------------------------------

   public static final int TYPE_INDEX = MESSAGE_ID_INDEX + 16;
   public static final int FLAG_INDEX = TYPE_INDEX + 2;

   public static int getLength(byte[] head)
   {
      return ByteBuffer.wrap(head)
            .getInt(LENGTH_INDEX);
   }

   public static short getType(byte[] head)
   {
      return ByteBuffer.wrap(head)
            .getShort(TYPE_INDEX);
   }

   public static byte[] getFlag(byte[] head)
   {
      return Arrays.copyOfRange(head, FLAG_INDEX, HEAD_LENGTH);
   }

   public static byte[] getMessageId(byte[] head)
   {
      return Arrays.copyOfRange(head, MESSAGE_ID_INDEX, TYPE_INDEX);
   }

   public static String getMacAddress(byte[] head)
   {
      byte[] macAddress = Arrays.copyOfRange(head, MAC_ADDRESS_INDEX, MAC_ADDRESS_END);
      StringBuilder sb = new StringBuilder();
      String delimiter = "";
      for (byte b : macAddress)
      {
         sb.append(delimiter)
               .append(String.format("%02x", b));
         delimiter = "-";
      }
      return sb.toString();
   }

   public static Date getMessageTime(byte[] head)
   {
      return new Date(ByteBuffer.wrap(Arrays.copyOfRange(head, TIME_INDEX, SERIAL_NO_INDEX))
            .getInt() * 1000L);
   }

   public static int getSerialNumber(byte[] head)
   {
      return ByteBuffer.wrap(Arrays.copyOfRange(head, SERIAL_NO_INDEX, HEAD_LENGTH))
            .getInt();
   }

   public static IncomingMessageType getMessageType(byte[] head)
   {
      return IncomingMessageType.fromTypeCode(getType(head));
   }
}
