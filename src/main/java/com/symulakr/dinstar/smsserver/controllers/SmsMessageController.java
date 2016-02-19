package com.symulakr.dinstar.smsserver.controllers;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.symulakr.dinstar.smsserver.HeadProvider;
import com.symulakr.dinstar.smsserver.MessageSender;
import com.symulakr.dinstar.smsserver.message.Message;
import com.symulakr.dinstar.smsserver.message.body.OutSmsMessage;
import com.symulakr.dinstar.smsserver.message.enums.SmsStatus;
import com.symulakr.dinstar.smsserver.message.head.Head;
import com.symulakr.dinstar.smsserver.message.head.MessageType;
import com.symulakr.dinstar.smsserver.models.SmsMessage;
import com.symulakr.dinstar.smsserver.models.SmsMessageDao;

@RestController
@RequestMapping("/sms")
public class SmsMessageController
{

   private final static Logger LOG = LogManager.getLogger(SmsMessageController.class);

   @Autowired
   private HeadProvider headProvider;

   @Autowired
   private MessageSender messageSender;

   @Autowired
   private SmsMessageDao smsMessageDao;

   @RequestMapping("/{number}/{message}")
   public String sendSmsMessage(@PathVariable("number") String number, @PathVariable("message") String message)
   {
      LOG.info("number: {} message: {}", number, message);
      OutSmsMessage outSmsMessage = new OutSmsMessage(number, message);
      SmsMessage smsMessage = new SmsMessage();
      smsMessage.setSrc("0");
      smsMessage.setDst(number);
      smsMessage.setContent(message);
      smsMessage.setDate(new Date());
      smsMessage.setStatus(SmsStatus.OUT);
      smsMessageDao.save(smsMessage);
      Head head = headProvider.provideHeader(outSmsMessage.bodyLength(), MessageType.SEND_SMS_REQUEST);
      Message sms = new Message(head, outSmsMessage);
      messageSender.sendMessage(sms);
      return "Ok";
   }

   @RequestMapping("")
   @ResponseBody
   public Iterable<SmsMessage> getSmsMessage()
   {
      return smsMessageDao.findAll();
   }

}
