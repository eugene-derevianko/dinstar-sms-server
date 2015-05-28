package com.symulakr.dinstar.smsserver.message.head;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Date;

import com.symulakr.dinstar.smsserver.common.ToBytes;

public class MessageId implements ToBytes
{

   public static final int LENGTH = 16;
   public static final int MAC_ADDRESS_INDEX = 0;
   public static final int TIME_INDEX = MAC_ADDRESS_INDEX + 8;
   public static final int SERIAL_NO_INDEX = TIME_INDEX + 4;

   private final byte[] bytes;

   private MacAddress macAddress;
   private Date messageTime;
   private int serialNo;

   public MessageId(MacAddress macAddress, Date messageTime, int serialNo)
   {
      this.macAddress = macAddress;
      this.messageTime = messageTime;
      this.serialNo = serialNo;
      this.bytes = ByteBuffer.allocate(LENGTH)
            .put(macAddress.toBytes())
            .putInt((int) (new Date().getTime() / 1000))
            .putInt(serialNo)
            .array();
   }

   public MessageId(byte[] bytes)
   {
      this.bytes = bytes;
      this.macAddress = new MacAddress(Arrays.copyOfRange(bytes, MAC_ADDRESS_INDEX, TIME_INDEX));
      this.messageTime = parseMessageTime();
      this.serialNo = parseSerialNumber();
   }

   public byte[] toBytes()
   {
      return bytes;
   }

   public MacAddress getMacAddress()
   {
      return macAddress;
   }

   public Date getMessageTime()
   {
      return messageTime;
   }

   public int getSerialNo()
   {
      return serialNo;
   }

   private Date parseMessageTime()
   {
      return new Date(ByteBuffer.wrap(Arrays.copyOfRange(bytes, TIME_INDEX, SERIAL_NO_INDEX))
            .getInt() * 1000L);
   }

   private int parseSerialNumber()
   {
      return ByteBuffer.wrap(Arrays.copyOfRange(bytes, SERIAL_NO_INDEX, LENGTH))
            .getInt();
   }

   @Override
   public final String toString()
   {
      return new StringBuilder().append("MAC address: ")
            .append(macAddress.getMacAddress())
            .append("\nTime: ")
            .append(messageTime)
            .append("\nSerial number: ")
            .append(serialNo)
            .toString();
   }

}
