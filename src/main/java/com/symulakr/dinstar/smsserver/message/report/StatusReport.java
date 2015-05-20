package com.symulakr.dinstar.smsserver.message.report;

import static com.symulakr.dinstar.smsserver.utils.EnumUtils.fromByte;

import com.symulakr.dinstar.smsserver.message.OutgoingMessage;
import com.symulakr.dinstar.smsserver.message.enums.SimStatus;

public class StatusReport extends AbstractStatusReport
{

   private SimStatus[] statuses;

   public StatusReport(byte[] head)
   {
      this.head = head;
   }

   @Override
   protected void parseBody()
   {
      super.parseBody();
      this.statuses = new SimStatus[this.portCount];
      for (int i = 0; i < portCount; i++)
      {
         statuses[i] = fromByte(portStatuses[i], SimStatus.values());
      }
   }

   @Override
   public OutgoingMessage createResponse()
   {
      return new StatusResponse(getMessageId(), true);
   }

   public SimStatus[] getStatuses()
   {
      return statuses;
   }
}
