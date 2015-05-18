package com.symulakr.dinstar.smsserver.message;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Date;

public class MessageId
{

   private final byte[] bytes;
   private String macAddress;
   private Date messageTime;
   private int serialNo;

   public MessageId(byte[] bytes)
   {
      this.bytes = bytes;
      this.macAddress = parseMacAddress();
      this.messageTime = parseMessageTime();
      this.serialNo = parseSerialNumber();
   }

   public byte[] toBytes()
   {
      return bytes;
   }

   public String getMacAddress()
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

   private String parseMacAddress()
   {
      StringBuilder sb = new StringBuilder();
      String delimiter = "";
      for (byte b : Arrays.copyOf(bytes, 6))
      {
         sb.append(delimiter)
               .append(String.format("%02x", b));
         delimiter = "-";
      }
      return sb.toString();
   }

   private Date parseMessageTime()
   {
      return new Date(ByteBuffer.wrap(Arrays.copyOfRange(bytes, 8, 12))
            .getInt() * 1000L);
   }

   private int parseSerialNumber()
   {
      return ByteBuffer.wrap(Arrays.copyOfRange(bytes, 12, 16))
            .getInt();
   }

   @Override
   public String toString() {
      return "Message Id:\n" +
              "\tMAC address: " + macAddress   +
              "\tTime: " + messageTime +
              "\tSerial number: " + serialNo;
   }

}
