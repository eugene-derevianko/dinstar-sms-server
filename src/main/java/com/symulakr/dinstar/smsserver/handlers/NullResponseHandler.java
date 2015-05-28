package com.symulakr.dinstar.smsserver.handlers;

import com.symulakr.dinstar.smsserver.message.Message;
import com.symulakr.dinstar.smsserver.message.body.ResponseBody;
import com.symulakr.dinstar.smsserver.message.head.MessageType;
import org.springframework.stereotype.Component;

@Component
public class NullResponseHandler extends Handler
{
   @Override
   protected MessageType getResponseMessageType()
   {
      return null;
   }

   @Override
   protected ResponseBody createBody(Message message)
   {
      return null;
   }

   @Override
   public Message processMessage(Message incomingMessage)
   {
      return null;
   }
}
