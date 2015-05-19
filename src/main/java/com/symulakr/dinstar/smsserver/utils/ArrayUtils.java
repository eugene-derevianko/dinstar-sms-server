package com.symulakr.dinstar.smsserver.utils;

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

}
