package com.symulakr.dinstar.smsserver.message;

public class DefaultMessage extends AbstractMessage
{

   public static final int TYPE = 0xFFFF;

   public DefaultMessage(Head head)
   {
      super(head);
   }

   @Override
   protected void parseBody()
   {
        //do nothing
   }

   @Override
   public int getType()
   {
      return TYPE;
   }
}
