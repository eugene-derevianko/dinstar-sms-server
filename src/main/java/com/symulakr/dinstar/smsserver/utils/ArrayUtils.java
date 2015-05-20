package com.symulakr.dinstar.smsserver.utils;

import java.util.Arrays;

public abstract class ArrayUtils
{

   public static String toString(byte[] array, int blockLength)
   {
      StringBuilder sb = new StringBuilder();
      String delimiter = "";
      for (int i = 0; i < array.length; i++)
      {
         if (i > 0 && i % blockLength == 0)
         {
            delimiter = "\n";
         }
         sb.append(delimiter)
               .append(String.format("%02x", array[i]));
         delimiter = " ";
      }
      return sb.toString();
   }

   public static String toString(byte[] array)
   {
      return toString(array, 16);
   }

   public static byte[] skip0x00(byte[] bytes)
   {
      return skipSomeByte(bytes, (byte) 0x00);
   }

   public static byte[] skipSomeByte(byte[] bytes, byte byteForSkipping)
   {
      int index = 0;
      byte[] temp = new byte[bytes.length];
      for (byte b : bytes)
      {
         if (b != byteForSkipping)
         {
            temp[index++] = b;
         }
      }
      return Arrays.copyOf(temp, index);
   }

}
