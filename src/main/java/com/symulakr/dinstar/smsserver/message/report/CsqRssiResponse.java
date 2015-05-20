package com.symulakr.dinstar.smsserver.message.report;

import com.symulakr.dinstar.smsserver.message.enums.OutgoingMessageType;
import com.symulakr.dinstar.smsserver.message.SimpleResponse;

public class CsqRssiResponse extends SimpleResponse
{

   public CsqRssiResponse(byte[] messageId, boolean succeed)
   {
      super(messageId, succeed);
   }

   @Override
   public short getType()
   {
      return OutgoingMessageType.X0E.getType();
   }

}
