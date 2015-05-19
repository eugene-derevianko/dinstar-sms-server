package com.symulakr.dinstar.smsserver.message;

public class DefaultMessage extends IncomingMessage
{

   public DefaultMessage(byte[] head)
   {
      this.head = head;
   }

   @Override
   protected void parseBody()
   {
      // do nothing
   }

   @Override
   public OutgoingMessage createResponse()
   {
      return null;
   }

}
