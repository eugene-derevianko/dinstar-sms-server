package com.symulakr.dinstar.smsserver.message;

import java.nio.ByteBuffer;

import com.symulakr.dinstar.smsserver.common.ToBytes;
import com.symulakr.dinstar.smsserver.message.head.Head;

public class Message implements ToBytes
{

   private Head head;
   private Body body;

   public Message(Head head, Body body)
   {
      this.head = head;
      this.body = body;
   }

   public Head getHead()
   {
      return head;
   }

   public Body getBody()
   {
      return body;
   }

   @Override
   public byte[] toBytes()
   {
      return ByteBuffer.allocate(Head.LENGTH + head.getLengthOfBody())
            .put(head.toBytes())
            .put(body.toBytes())
            .array();
   }
}
