package com.symulakr.dinstar.smsserver.message.ussd;

import com.symulakr.dinstar.smsserver.message.SimpleResponse;
import com.symulakr.dinstar.smsserver.message.enums.OutgoingMessageType;

public class IncomingUssdMessageResponse extends SimpleResponse
{
   public IncomingUssdMessageResponse(byte[] messageId, boolean succeed)
   {
      super(messageId, succeed);
   }

   @Override
   public short getType()
   {
      return OutgoingMessageType.X0C.getType();
   }
}
