package com.symulakr.dinstar.smsserver.handlers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.symulakr.dinstar.smsserver.message.Message;
import com.symulakr.dinstar.smsserver.message.body.SendSmsResult;
import com.symulakr.dinstar.smsserver.message.head.MessageType;

@Component
public class SendSmsResultHandler extends SimpleResponseHandler
{

   private final static Logger LOG = LogManager.getLogger(SendSmsResultHandler.class);

   public MessageType getResponseMessageType()
   {
      return MessageType.RESPONSE_TO_RECEIVE_SMS_MESSAGE;
   }

   @Override
   protected boolean isOk(Message message)
   {
      LOG.info(message);
      SendSmsResult smsResult = new SendSmsResult(message.getBody());
      LOG.info(smsResult);
      return true;
   }
}
