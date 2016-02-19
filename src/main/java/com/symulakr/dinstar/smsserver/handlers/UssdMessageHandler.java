package com.symulakr.dinstar.smsserver.handlers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.symulakr.dinstar.smsserver.message.Message;
import com.symulakr.dinstar.smsserver.message.body.UssdMessage;
import com.symulakr.dinstar.smsserver.message.head.MessageType;

@Component
public class UssdMessageHandler extends SimpleResponseHandler
{

   private final static Logger LOG = LogManager.getLogger(UssdMessageHandler.class);

   public MessageType getResponseMessageType()
   {
      return MessageType.RESPONSE_TO_RECEIVE_USSD_MESSAGE;
   }

   @Override
   protected boolean isOk(Message message)
   {
      UssdMessage ussdMessage = new UssdMessage(message.getBody());
      LOG.info(ussdMessage);
      return true;
   }
}
