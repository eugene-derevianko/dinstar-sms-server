package com.symulakr.dinstar.smsserver.message.report;

import com.symulakr.dinstar.smsserver.message.OutgoingMessage;

public class CsqRssiReport extends AbstractStatusReport
{

   public CsqRssiReport(byte[] head)
   {
      this.head = head;
   }

   @Override
   public OutgoingMessage createResponse()
   {
      return new CsqRssiResponse(getMessageId(), true);
   }

}
