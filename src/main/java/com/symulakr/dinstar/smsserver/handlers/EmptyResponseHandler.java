package com.symulakr.dinstar.smsserver.handlers;

import org.springframework.stereotype.Component;

import com.symulakr.dinstar.smsserver.message.Message;
import com.symulakr.dinstar.smsserver.message.head.MessageType;

@Component
public class EmptyResponseHandler extends SimpleResponseHandler
{
   @Override
   protected MessageType getResponseMessageType()
   {
      return MessageType.DEFAULT;
   }

   @Override
   protected boolean isOk(Message message)
   {
      return true;
   }
}
