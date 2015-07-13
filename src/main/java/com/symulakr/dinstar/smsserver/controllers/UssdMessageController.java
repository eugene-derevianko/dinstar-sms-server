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
import com.symulakr.dinstar.smsserver.message.body.UssdRequest;
import com.symulakr.dinstar.smsserver.message.head.Head;
import com.symulakr.dinstar.smsserver.message.head.MessageType;

@RestController
public class UssdMessageController
{

   private final static Logger LOG = LogManager.getLogger(UssdMessageController.class);

   @Autowired
   private HeadProvider headProvider;

   @Autowired
   private MessageSender messageSender;

   @RequestMapping("/ussd/{content}")
   public String ussdrequest(@PathVariable("content") String content)
   {
      LOG.info("USSD request with content: {}", content);
      UssdRequest ussdRequest = new UssdRequest(content);
      Head head = headProvider.provideHeader(ussdRequest.bodyLength(), MessageType.X09);
      Message message = new Message(head, ussdRequest);
      messageSender.sendMessage(message);
      return "Ussd sent";
   }

}
