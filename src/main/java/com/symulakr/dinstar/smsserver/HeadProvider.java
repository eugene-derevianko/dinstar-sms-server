package com.symulakr.dinstar.smsserver;

import com.symulakr.dinstar.smsserver.message.enums.MessageType;
import com.symulakr.dinstar.smsserver.utils.HeadParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.ByteBuffer;

@Component
public class HeadProvider
{

   @Autowired
   private MacAddressProvider macAddressProvider;
   @Autowired
   private SerialNumberProvider serialNumberProvider;
   @Autowired
   private FlagProvider flagProvider;

   public byte[] provideHeader(byte[]head, int lengthOfBody, MessageType messageType)
   {
      return ByteBuffer.allocate(HeadParser.HEAD_LENGTH)
            .putInt(lengthOfBody)
            .put(macAddressProvider.getMacAddress())
            .putShort(messageType.getType())
            .put(flagProvider.getFlag())
            .array();
   }



}
