package com.symulakr.dinstar.smsserver;

import org.springframework.stereotype.Component;

@Component
public class SerialNumberProvider
{

   private static int serialNo = 1;

   public int getSerialNumber()
   {
      return serialNo++;
   }

}
