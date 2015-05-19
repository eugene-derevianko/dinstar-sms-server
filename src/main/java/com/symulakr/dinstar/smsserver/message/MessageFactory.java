package com.symulakr.dinstar.smsserver.message;

import java.io.BufferedInputStream;
import java.io.IOException;

import com.symulakr.dinstar.smsserver.message.report.CsqRssiReport;
import com.symulakr.dinstar.smsserver.message.report.StatusReport;
import com.symulakr.dinstar.smsserver.message.service.AuthenticationRequest;
import com.symulakr.dinstar.smsserver.message.sms.IncomingGsmMessage;
import com.symulakr.dinstar.smsserver.utils.HeadParser;

public class MessageFactory
{

   public IncomingMessage getMessage(BufferedInputStream stream) throws IOException, InterruptedException
   {
      while (stream.available() < HeadParser.HEAD_LENGTH)
      {
         Thread.sleep(1000);
      }
      byte[] head = new byte[HeadParser.HEAD_LENGTH];
      stream.read(head);
      com.symulakr.dinstar.smsserver.message.IncomingMessage message = createMessage(head);
      message.readBody(stream);
      return message;
   }

   private com.symulakr.dinstar.smsserver.message.IncomingMessage createMessage(byte[] head)
   {
      switch (HeadParser.getMessageType(head))
      {
         case X05:
            return new IncomingGsmMessage(head);
         case X07:
            return new StatusReport(head);
         case X0D:
            return new CsqRssiReport(head);
         case X0F:
            return new AuthenticationRequest(head);
         default:
            return new DefaultMessage(head);

      }
   }

}
