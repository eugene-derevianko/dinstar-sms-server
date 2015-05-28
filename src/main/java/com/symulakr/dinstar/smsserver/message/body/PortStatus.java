package com.symulakr.dinstar.smsserver.message.body;

import static com.symulakr.dinstar.smsserver.utils.EnumUtils.fromByte;

import com.symulakr.dinstar.smsserver.common.ToBytes;
import com.symulakr.dinstar.smsserver.message.enums.SimStatus;

public class PortStatus extends AbstractStatusBody
{

   private SimStatus[] statuses;

   public PortStatus(ToBytes body)
   {
      super(body);
   }

   @Override
   protected void parseByteArray(byte[] body)
   {
      super.parseByteArray(body);
      this.statuses = new SimStatus[this.portCount];
      for (int i = 0; i < portCount; i++)
      {
         statuses[i] = fromByte(portStatuses[i], SimStatus.values());
      }
   }

   public SimStatus[] getStatuses()
   {
      return statuses;
   }

}
