package com.symulakr.dinstar.smsserver.message.head;

import java.nio.ByteBuffer;
import java.util.Arrays;

import com.symulakr.dinstar.smsserver.common.ToBytes;

public class MacAddress implements ToBytes
{

   private byte[] bytes;
   private String macAddress;

   public MacAddress(byte[] bytes)
   {
      this.bytes = bytes;
      convertToString();
   }

   public MacAddress(String macAddress)
   {
      this.macAddress = macAddress;
      convertToBytes();
   }

   @Override
   public byte[] toBytes()
   {
      return bytes;
   }

   public String getMacAddress()
   {
      return macAddress;
   }

   private void convertToString()
   {
      StringBuilder sb = new StringBuilder();
      String delimiter = "";
      for (byte b : Arrays.copyOf(bytes, 6))
      {
         sb.append(delimiter)
               .append(String.format("%02x", b));
         delimiter = "-";
      }
      macAddress = sb.toString();
   }

   private void convertToBytes()
   {
      String[] splittedMacAddress = macAddress.split("[-:]", 6);
      ByteBuffer byteBuffer = ByteBuffer.allocate(6);
      for (String part : splittedMacAddress)
      {
         byteBuffer.put(Byte.parseByte(part, 16));
      }
      byteBuffer.put(new byte[]
      {
            0, 0
      }); // MAC(8 byte,last 2 byte not used)
      bytes = byteBuffer.array();
   }

}
