package com.symulakr.dinstar.smsserver;

import org.springframework.stereotype.Component;

@Component
public class FlagProvider
{

   public byte[] getFlag()
   {
      return new byte[]
      {
            0, 0
      };
   }

}
