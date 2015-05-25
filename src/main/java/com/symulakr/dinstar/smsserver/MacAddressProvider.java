package com.symulakr.dinstar.smsserver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MacAddressProvider
{

   @Value("${sms.server.mac}")
   private String macAddress;

   public static byte getMacAddress()
   {
      return Byte.parseByte("45");
   }

   public static void main(String[] args)
   {
      System.out.println(getMacAddress());
   }

}
