package com.symulakr.dinstar.smsserver.message;

import com.symulakr.dinstar.smsserver.message.report.CsqRssiReport;
import com.symulakr.dinstar.smsserver.message.report.StatusReport;
import com.symulakr.dinstar.smsserver.message.service.AuthenticationRequest;
import com.symulakr.dinstar.smsserver.message.sms.IncomingGsmMessage;
import com.symulakr.dinstar.smsserver.message.ussd.IncomingUssdMessage;
import com.symulakr.dinstar.smsserver.utils.HeadParser;

public class MessageFactory
{

   public IncomingMessage createMessage(byte[] head)
   {
      switch (HeadParser.getMessageType(head))
      {
         case X05:
            return new IncomingGsmMessage(head);
         case X07:
            return new StatusReport(head);
         case X0B:
            return new IncomingUssdMessage(head);
         case X0D:
            return new CsqRssiReport(head);
         case X0F:
            return new AuthenticationRequest(head);
         default:
            return new DefaultMessage(head);
      }
   }

}
