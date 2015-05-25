package com.symulakr.dinstar.smsserver.message.ussd;

import com.symulakr.dinstar.smsserver.dto.Port;
import com.symulakr.dinstar.smsserver.message.OutgoingMessage;
import com.symulakr.dinstar.smsserver.message.enums.OutgoingMessageType;

public class UssdRequest extends OutgoingMessage
{

   private Port port;
   private UssdType ussdType;
   private String content = "";

   public UssdRequest(Port port, UssdType ussdType, String content)
   {
      this.port = port;
      this.ussdType = ussdType;
      this.content = content;
   }

   public UssdRequest(Port port, String content)
   {
      this(port, UssdType.SEND, content);
   }

   @Override
   protected void createBody()
   {

   }

   @Override
   public int getLength()
   {
      return content.length();
   }

   @Override
   public short getType()
   {
      return OutgoingMessageType.X09.getType();
   }
}
