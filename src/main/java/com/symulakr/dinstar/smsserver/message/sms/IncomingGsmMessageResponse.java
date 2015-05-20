package com.symulakr.dinstar.smsserver.message.sms;

import com.symulakr.dinstar.smsserver.message.enums.OutgoingMessageType;
import com.symulakr.dinstar.smsserver.message.SimpleResponse;

public class IncomingGsmMessageResponse extends SimpleResponse
{
   public IncomingGsmMessageResponse(byte[] messageId, boolean succeed)
   {
      super(messageId, succeed);
   }

   @Override
   public short getType()
   {
      return OutgoingMessageType.X06.getType();
   }
}
