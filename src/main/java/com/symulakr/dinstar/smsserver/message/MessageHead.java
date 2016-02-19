package com.symulakr.dinstar.smsserver.message;

import java.nio.ByteBuffer;
import java.util.Date;

import com.symulakr.dinstar.smsserver.common.ToBytes;
import com.symulakr.dinstar.smsserver.message.head.MacAddress;
import com.symulakr.dinstar.smsserver.message.head.MessageType;

public class MessageHead implements ToBytes
{

   private ByteBuffer buffer;
   private int lengthOfBody;
   private MacAddress macAddress;
   private Date date;
   private int serialNo;
   private MessageType type;
   private short flag;

   public MessageHead(ByteBuffer buffer)
   {
      this.buffer = buffer;
      lengthOfBody = buffer.getInt();
      byte[] bytes = new byte[8];
      buffer.get(bytes);
      macAddress = new MacAddress(bytes);
      date = new Date(buffer.getInt() * 1000L);
      serialNo = buffer.getInt();
      type = MessageType.decode(buffer.getShort());
      flag = buffer.getShort();
   }

   public int getLengthOfBody()
   {
      return lengthOfBody;
   }

   public MacAddress getMacAddress()
   {
      return macAddress;
   }

   public Date getDate()
   {
      return date;
   }

   public int getSerialNo()
   {
      return serialNo;
   }

   public MessageType getType()
   {
      return type;
   }

   public short getFlag()
   {
      return flag;
   }

   @Override
   public byte[] toBytes()
   {
      return buffer.array();
   }
}
