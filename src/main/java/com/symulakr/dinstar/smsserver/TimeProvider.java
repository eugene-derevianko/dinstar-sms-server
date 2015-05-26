package com.symulakr.dinstar.smsserver;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class TimeProvider
{

   public Date provideTime()
   {
      return new Date();
   }

}
