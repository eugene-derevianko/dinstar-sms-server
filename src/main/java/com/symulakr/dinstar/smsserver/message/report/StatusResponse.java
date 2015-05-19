package com.symulakr.dinstar.smsserver.message.report;

import com.symulakr.dinstar.smsserver.message.OutgoingMessageType;
import com.symulakr.dinstar.smsserver.message.SimpleResponse;

public class StatusResponse extends SimpleResponse
{

   public StatusResponse(byte[] messageId, boolean succeed)
   {
      super(messageId, succeed);
   }

   @Override
   public short getType()
   {
      return OutgoingMessageType.X08.getType();
   }

}
