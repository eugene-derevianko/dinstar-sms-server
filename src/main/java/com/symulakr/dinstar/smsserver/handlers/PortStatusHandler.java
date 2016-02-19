package com.symulakr.dinstar.smsserver.handlers;

import org.springframework.stereotype.Component;

import com.symulakr.dinstar.smsserver.message.Message;
import com.symulakr.dinstar.smsserver.message.body.PortStatus;
import com.symulakr.dinstar.smsserver.message.head.MessageType;

@Component
public class PortStatusHandler extends SimpleResponseHandler
{

   public MessageType getResponseMessageType()
   {
      return MessageType.RESPONSE_TO_STATUS_REPORT;
   }

   @Override
   protected boolean isOk(Message message)
   {
      PortStatus messBody = new PortStatus(message.getBody());
      return true;
   }

}
