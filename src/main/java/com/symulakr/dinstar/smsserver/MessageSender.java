package com.symulakr.dinstar.smsserver;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.symulakr.dinstar.smsserver.message.Message;
import com.symulakr.dinstar.smsserver.message.MessageFuture;

@Component
public class MessageSender
{

   @Autowired
   private ConnectionSocket connectionSocket;

   private final Map<Integer, MessageFuture<Message>> messageFutures = Collections.synchronizedMap(new WeakHashMap<>());

   private long defaultReplyTimeout = 30L;

   private final static Logger LOG = LogManager.getLogger(MessageSender.class);

   public void sendMessage(Message message)
   {
      LOG.info("Send message\n{}", message);
      try
      {
         ByteArrayOutputStream outToClient = new ByteArrayOutputStream();
         outToClient.write(message.toBytes());
         outToClient.writeTo(connectionSocket.getOutputStream());
      }
      catch (IOException ex)
      {
         LOG.error(ex);
      }
   }

   public Message sendAndReceive(Message message) throws TimeoutException
   {
      return sendAndReceive(message, defaultReplyTimeout);
   }

   public Message sendAndReceive(Message message, long replyTimeout) throws TimeoutException
   {
      return sendAndReceive(message, (Integer) null, replyTimeout);
   }

   public Message sendAndReceive(Message message, Integer messageTtl, long replyTimeout) throws TimeoutException
   {

      MessageFuture<Message> messageFuture = new MessageFuture<>();
      Integer correlationId = message.getHead().getMessageId().getSerialNo();
      messageFutures.put(correlationId, messageFuture);

      sendMessage(message);

      try
      {
         return waitForResponse(messageFuture, replyTimeout);
      }
      catch (TimeoutException e)
      {
         messageFutures.remove(correlationId);
         throw e;
      }
   }

   private Message waitForResponse(MessageFuture<Message> messageFuture, long replyTimeout) throws TimeoutException
   {
      try
      {
         return messageFuture.get(replyTimeout, TimeUnit.SECONDS);
      }
      catch (InterruptedException e)
      {
         Thread.currentThread()
               .interrupt();
         return null;
      }
   }

   public void onResponseMessage(Integer correlationId, Message message)
   {
      if (correlationId == null)
      {
         LOG.warn("Missing correlation ID of response message {}", message);
         return;
      }
      MessageFuture<Message> messageFuture = messageFutures.remove(correlationId);
      if (messageFuture != null)
      {
         try
         {
            messageFuture.complete(message);
         }
         catch (InterruptedException e)
         {
            Thread.currentThread()
                  .interrupt();
         }
      }
      else
      {
         LOG.debug("No current request message found for correlation ID {} related to response message {}", correlationId, message);
      }
   }

}
