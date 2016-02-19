package com.symulakr.dinstar.smsserver.handlers;

import org.springframework.stereotype.Component;

import com.symulakr.dinstar.smsserver.message.Message;
import com.symulakr.dinstar.smsserver.message.body.AuthenticationRequestBody;
import com.symulakr.dinstar.smsserver.message.head.MessageType;

@Component
public class AuthenticationHandler extends SimpleResponseHandler
{

   public MessageType getResponseMessageType()
   {
      return MessageType.RESPONSE_TO_SEND_AUTHENTICATION;
   }

   @Override
   protected boolean isOk(Message message)
   {
      AuthenticationRequestBody messBody = new AuthenticationRequestBody(message.getBody());
      return true;
   }
}
