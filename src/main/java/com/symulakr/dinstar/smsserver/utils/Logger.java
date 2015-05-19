package com.symulakr.dinstar.smsserver.utils;

import com.symulakr.dinstar.smsserver.message.AbstractMessage;

public abstract class Logger
{

   public static void sout(AbstractMessage message)
   {
      switch (message.getType())
      {
         case 0x0F:
         case 0x10:
         case 0x05:
         case 0x06:
            System.out.println(message);
            break;
         default:
            break;
      }
   }

}
