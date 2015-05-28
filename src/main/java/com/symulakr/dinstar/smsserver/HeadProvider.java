package com.symulakr.dinstar.smsserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.symulakr.dinstar.smsserver.message.head.Head;
import com.symulakr.dinstar.smsserver.message.head.MessageType;

@Component
public class HeadProvider
{

   @Autowired
   private MessageIdProvider messageIdProvider;
   @Autowired
   private FlagProvider flagProvider;

   public Head provideHeader(int lengthOfBody, MessageType messageType)
   {
      return new Head(lengthOfBody, messageIdProvider.provideMessageId(), messageType, flagProvider.provideFlag());
   }

}
