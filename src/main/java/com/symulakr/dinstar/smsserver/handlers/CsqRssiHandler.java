package com.symulakr.dinstar.smsserver.handlers;

import org.springframework.stereotype.Component;

import com.symulakr.dinstar.smsserver.message.Message;
import com.symulakr.dinstar.smsserver.message.body.CsqRssiBody;
import com.symulakr.dinstar.smsserver.message.head.MessageType;

@Component
public class CsqRssiHandler extends SimpleResponseHandler
{

   public MessageType getResponseMessageType()
   {
      return MessageType.X0E;
   }

   @Override
   protected boolean isOk(Message message)
   {
      CsqRssiBody messBody = new CsqRssiBody(message.getBody());
      return true;
   }
}
