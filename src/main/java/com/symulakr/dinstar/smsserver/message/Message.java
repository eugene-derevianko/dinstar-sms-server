package com.symulakr.dinstar.smsserver.message;

import java.nio.ByteBuffer;
import java.util.Arrays;

import com.symulakr.dinstar.smsserver.common.ToBytes;
import com.symulakr.dinstar.smsserver.message.head.Head;

public class Message implements ToBytes
{

   private Head head;
   private ToBytes body;

   public Message(Head head, ToBytes body)
   {
      this.head = head;
      this.body = body;
   }

   public Head getHead()
   {
      return head;
   }

   public ToBytes getBody()
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

   @Override
   public String toString()
   {
      return "Message{" +
            "head=" + head +
            ", body=" + Arrays.toString(body.toBytes()) +
            '}';
   }
}
