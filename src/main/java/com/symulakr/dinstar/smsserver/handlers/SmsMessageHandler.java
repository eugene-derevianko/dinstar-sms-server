package com.symulakr.dinstar.smsserver.handlers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.symulakr.dinstar.smsserver.message.Message;
import com.symulakr.dinstar.smsserver.message.body.SmsMessage;
import com.symulakr.dinstar.smsserver.message.head.MessageType;

@Component
public class SmsMessageHandler extends SimpleResponseHandler
{

   private final static Logger LOG = LogManager.getLogger(SmsMessageHandler.class);

   public MessageType getResponseMessageType()
   {
      return MessageType.X06;
   }

   @Override
   protected boolean isOk(Message message)
   {
      SmsMessage smsMessage = new SmsMessage(message.getBody());
      LOG.info(smsMessage);
      return true;
   }
}
