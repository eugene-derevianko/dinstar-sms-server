package com.symulakr.dinstar.smsserver.common;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.SynchronousQueue;

import com.symulakr.dinstar.smsserver.message.head.MessageType;
import org.springframework.stereotype.Component;

import com.symulakr.dinstar.smsserver.message.MessageHead;

@Component
public class ResponsesPool
{

   private Map<MessageType, SynchronousQueue<MessageHead>> pool = new HashMap<>();

   public MessageHead takeResponse(MessageType type) throws InterruptedException
   {
      return getQueueForType(type).take();
   }

   public void putResponse(MessageHead message) throws InterruptedException
   {
      getQueueForType(message.getType()).put(message);
   }

   private SynchronousQueue<MessageHead> getQueueForType(MessageType type)
   {
      SynchronousQueue<MessageHead> queue = pool.get(type);
      return queue != null ? queue : addQueueForType(type);
   }

   private SynchronousQueue<MessageHead> addQueueForType(MessageType type)
   {
      SynchronousQueue<MessageHead> queue = new SynchronousQueue<>();
      pool.put(type, queue);
      return queue;
   }

}
