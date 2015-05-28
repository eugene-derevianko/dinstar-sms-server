package com.symulakr.dinstar.smsserver.handlers;

import com.symulakr.dinstar.smsserver.message.Message;
import com.symulakr.dinstar.smsserver.message.body.ResponseBody;
import com.symulakr.dinstar.smsserver.message.head.Flag;
import com.symulakr.dinstar.smsserver.message.head.Head;
import com.symulakr.dinstar.smsserver.message.head.MessageId;
import com.symulakr.dinstar.smsserver.message.head.MessageType;

public abstract class Handler
{

   protected abstract MessageType getResponseMessageType();

   protected abstract ResponseBody createBody(Message message);

   public Message processMessage(Message incomingMessage)
   {
      Head incomingHead = incomingMessage.getHead();
      MessageId messageId = incomingHead.getMessageId();
      ResponseBody body = createBody(incomingMessage);
      Head head = new Head(body.getLength(), messageId, getResponseMessageType(), new Flag());
      return new Message(head, body);
   }

}
