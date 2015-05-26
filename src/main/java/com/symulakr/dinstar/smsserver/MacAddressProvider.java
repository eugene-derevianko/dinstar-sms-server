package com.symulakr.dinstar.smsserver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.symulakr.dinstar.smsserver.message.head.MacAddress;

@Component
public class MacAddressProvider
{

   @Value("${sms.server.mac}")
   private String macAddressString;

   private MacAddress macAddress;

   public MacAddress provideMacAddress()
   {
      if (macAddress == null)
      {
         macAddress = new MacAddress(macAddressString);
      }
      return macAddress;
   }

   public String getMacAddressString()
   {
      return macAddressString;
   }
}
