package com.symulakr.dinstar.smsserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.symulakr.dinstar.smsserver.message.head.MessageId;

@Component
public class MessageIdProvider
{

   @Autowired
   private MacAddressProvider macAddressProvider;
   @Autowired
   private TimeProvider timeProvider;
   @Autowired
   private SerialNumberProvider serialNumberProvider;

   public MessageId provideMessageId()
   {
      return new MessageId(macAddressProvider.provideMacAddress(), timeProvider.provideTime(), serialNumberProvider.getSerialNumber());
   }

}
