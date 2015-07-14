package com.symulakr.dinstar.smsserver.message;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class MessageFuture<V> implements Future<V>
{

   private final BlockingQueue<V> queue = new ArrayBlockingQueue<>(1);

   @Override
   public boolean cancel(boolean mayInterruptIfRunning)
   {
      throw new NotImplementedException();
   }

   @Override
   public boolean isCancelled()
   {
      throw new NotImplementedException();
   }

   @Override
   public boolean isDone()
   {
      throw new NotImplementedException();
   }

   @Override
   public V get() throws InterruptedException, ExecutionException
   {
      return this.queue.take();
   }

   @Override
   public V get(long timeout, TimeUnit unit) throws InterruptedException, TimeoutException
   {
      final V result = queue.poll(timeout, unit);
      if (result == null)
      {
         throw new TimeoutException();
      }
      return result;
   }

   public void complete(V result) throws InterruptedException
   {
      queue.put(result);
   }

}
