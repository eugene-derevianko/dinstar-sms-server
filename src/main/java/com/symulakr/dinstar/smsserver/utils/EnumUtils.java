package com.symulakr.dinstar.smsserver.utils;

import com.symulakr.dinstar.smsserver.message.enums.AsByte;

public abstract class EnumUtils
{

   public static <E extends AsByte> E fromByte(byte b, E[] enums)
   {
      for (E asByte : enums)
      {
         if (asByte.asByte() == b)
         {
            return asByte;
         }
      }
      return null;
   }

}
