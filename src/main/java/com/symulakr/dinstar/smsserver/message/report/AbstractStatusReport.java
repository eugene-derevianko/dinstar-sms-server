package com.symulakr.dinstar.smsserver.message.report;

import java.util.Arrays;

import com.symulakr.dinstar.smsserver.message.IncomingMessage;

public abstract class AbstractStatusReport extends IncomingMessage
{

   protected int portCount;
   protected byte[] portStatuses;

   public int getPortCount()
   {
      return portCount;
   }

   public byte[] getPortStatuses()
   {
      return portStatuses;
   }

   @Override
   protected void parseBody()
   {
      this.portCount = body[0];
      this.portStatuses = Arrays.copyOfRange(body, 1, this.portCount + 1);
   }

   @Override
   public String toString()
   {
      return new StringBuilder(super.toString()).append("\n\tPort Count: ")
            .append(portCount)
            .append("\n\tStatus: ")
            .append(Arrays.toString(portStatuses))
            .toString();
   }
}