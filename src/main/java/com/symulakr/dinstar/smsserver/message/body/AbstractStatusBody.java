package com.symulakr.dinstar.smsserver.message.body;

import java.util.Arrays;

import com.symulakr.dinstar.smsserver.common.ToBytes;

public abstract class AbstractStatusBody extends BodyFromDWG
{

   protected int portCount;
   protected byte[] portStatuses;

   public AbstractStatusBody(ToBytes body)
   {
      super(body);
   }

   public int getPortCount()
   {
      return portCount;
   }

   public byte[] getPortStatuses()
   {
      return portStatuses;
   }

   @Override
   protected void parseByteArray(byte[] body)
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