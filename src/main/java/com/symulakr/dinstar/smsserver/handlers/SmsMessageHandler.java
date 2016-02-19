package com.symulakr.dinstar.smsserver.handlers;

import com.symulakr.dinstar.smsserver.models.SmsMessage;
import com.symulakr.dinstar.smsserver.models.SmsMessageDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.symulakr.dinstar.smsserver.controllers.SmsMessageController;
import com.symulakr.dinstar.smsserver.message.Message;
import com.symulakr.dinstar.smsserver.message.body.ReceiveSms;
import com.symulakr.dinstar.smsserver.message.head.MessageType;

@Component
public class SmsMessageHandler extends SimpleResponseHandler
{


   @Autowired
   private SmsMessageDao smsMessageDao;
   // Temp --------------------------------------
   @Autowired
   private SmsMessageController smsMessageController;
   @Value("${sms.forward.number}")
   private String forwardNumber;
   // Temp --------------------------------------


   private final static Logger LOG = LogManager.getLogger(SmsMessageHandler.class);

   public MessageType getResponseMessageType()
   {
      return MessageType.RESPONSE_TO_RECEIVE_SMS_MESSAGE;
   }

   @Override
   protected boolean isOk(Message message)
   {
      ReceiveSms receiveSms = new ReceiveSms(message.getBody());
      LOG.info(receiveSms);
      smsMessageDao.save(new SmsMessage(receiveSms));
      // Temp --------------------------------------
//      smsMessageController.sendSmsMessage(forwardNumber, receiveSms.getNumber() + ": " + receiveSms.getContent());
      // Temp --------------------------------------
      return true;
   }
}
