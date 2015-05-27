package com.symulakr.dinstar.smsserver;

import com.symulakr.dinstar.smsserver.message.head.Flag;
import org.springframework.stereotype.Component;

@Component
public class FlagProvider
{

   public Flag provideFlag()
   {
      return new Flag();
   }

}
