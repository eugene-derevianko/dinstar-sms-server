package com.symulakr.dinstar.smsserver.handlers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.symulakr.dinstar.smsserver.controllers.SmsMessageController;
import com.symulakr.dinstar.smsserver.message.Message;
import com.symulakr.dinstar.smsserver.message.body.SmsMessage;
import com.symulakr.dinstar.smsserver.message.head.MessageType;

@Component
public class SmsMessageHandler extends SimpleResponseHandler
{

   // Temp --------------------------------------
   @Autowired
   private SmsMessageController smsMessageController;
   @Value("${sms.forward.number}")
   private String forwardNumber;
   // Temp --------------------------------------


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
      // Temp --------------------------------------
      smsMessageController.sendSmsMessage(forwardNumber, smsMessage.getNumber() + ": " + smsMessage.getContent());
      // Temp --------------------------------------
      return true;
   }
}
