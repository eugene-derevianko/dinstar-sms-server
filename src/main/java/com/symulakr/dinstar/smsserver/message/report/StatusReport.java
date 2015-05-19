package com.symulakr.dinstar.smsserver.message.report;

import com.symulakr.dinstar.smsserver.message.OutgoingMessage;

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
   }

   @Override
   public OutgoingMessage createResponse()
   {
      return new StatusResponse(getMessageId(), true);
   }

   public static enum SimStatus
   {
      WORKS(0),
      NO_SIM(1),
      NOT_REGISTRED(2),
      UNAVAILABLE(3);

      private int status;

      SimStatus(int status)
      {
         this.status = status;
      }

   }

}
