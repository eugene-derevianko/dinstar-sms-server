package com.symulakr.dinstar.smsserver.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.symulakr.dinstar.smsserver.HeadProvider;
import com.symulakr.dinstar.smsserver.MessageSender;
import com.symulakr.dinstar.smsserver.message.Message;
import com.symulakr.dinstar.smsserver.message.body.OutSmsMessage;
import com.symulakr.dinstar.smsserver.message.head.Head;
import com.symulakr.dinstar.smsserver.message.head.MessageType;

//@RestController
public class SmsMessageController
{

   private final static Logger LOG = LogManager.getLogger(SmsMessageController.class);

   @Autowired
   private HeadProvider headProvider;

   @Autowired
   private MessageSender messageSender;

   @RequestMapping("/sms/{number}/{message}")
   public String sendSmsMessage(@PathVariable("number") String number, @PathVariable("message") String message)
   {
      LOG.info("number: {} message: {}", number, message);
      OutSmsMessage outSmsMessage = new OutSmsMessage(number, message);
      Head head = headProvider.provideHeader(outSmsMessage.bodyLength(), MessageType.X01);
      Message sms = new Message(head, outSmsMessage);
      messageSender.sendMessage(sms);

      return "Ok";
   }

}
