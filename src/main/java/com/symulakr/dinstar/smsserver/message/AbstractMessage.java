package com.symulakr.dinstar.smsserver.message;

import java.nio.ByteBuffer;

import com.symulakr.dinstar.smsserver.utils.ArrayUtils;
import com.symulakr.dinstar.smsserver.utils.HeadParser;

public abstract class AbstractMessage implements Message
{

   protected byte[] head;
   protected byte[] body;

   public byte[] getHead()
   {
      return head;
   }

   public byte[] getBody()
   {
      return body;
   }

   public abstract int getLength();

   public abstract short getType();

   public abstract byte[] getFlag();

   public byte[] toBytes()
   {
      return ByteBuffer.allocate(head.length + body.length)
            .put(head)
            .put(body)
            .array();
   }

   @Override
   public String toString()
   {
      return new StringBuilder("\n-----------------------------------------------")
            .append("\nHead:")
            .append("\n-----------------------------------------------\n")
            .append(ArrayUtils.toString(head))
            .append("\n-----------------------------------------------")
            .append("\n\tLength: ")
            .append(getLength())
            .append("\n\tMessage Id:")
            .append("\n\t\tMAC address: ")
            .append(HeadParser.getMacAddress(head))
            .append("\n\t\tTime: ")
            .append(HeadParser.getMessageTime(head))
            .append("\n\t\tSerial number: ")
            .append(HeadParser.getSerialNumber(head))
            .append("\n\tType: ")
            .append(String.format("%04x", getType()))
            .append("\n\tFlag: ")
            .append(ArrayUtils.toString(getFlag()))
            .append("\nBody:")
            .append("\n-----------------------------------------------\n")
            .append(ArrayUtils.toString(body))
            .append("\n-----------------------------------------------")
            .toString();
   }

}
