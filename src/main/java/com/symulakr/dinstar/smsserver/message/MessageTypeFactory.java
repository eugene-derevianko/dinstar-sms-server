package com.symulakr.dinstar.smsserver.message;

public class MessageTypeFactory
{

   public static MessageType getMessageType(int type)
   {
      switch (type)
      {
         case 0x00:
            return MessageType.X00;
         case 0x01:
            return MessageType.X01;
         case 0x0D:
            return MessageType.X0D;
         case 0x0F:
            return MessageType.X0F;
         case 0x10:
            return MessageType.X10;
         default:
            return null;
      }
   }

}
