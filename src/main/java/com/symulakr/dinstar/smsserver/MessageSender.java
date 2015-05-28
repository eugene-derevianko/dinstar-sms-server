package com.symulakr.dinstar.smsserver;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.symulakr.dinstar.smsserver.message.Message;

@Component
public class MessageSender extends Thread
{

   @Autowired
   private ConnectionSocket connectionSocket;
   @Autowired
   private OutgoingQueue outgoingQueue;

   private final static Logger LOG = LogManager.getLogger(MessageSender.class);

   private boolean started = false;

   @Override
   public void run()
   {
      LOG.info("MessageSender started");
      try
      {
         while (started)
         {
            while (!outgoingQueue.isEmpty())
            {
               Message message = outgoingQueue.poll();
               ByteArrayOutputStream outToClient = new ByteArrayOutputStream();
               outToClient.write(message.toBytes());
               outToClient.writeTo(connectionSocket.getOutputStream());
            }
         }
      }
      catch (IOException ex)
      {
         LOG.error(ex);
      }
   }

   @PreDestroy
   public void onStop() throws IOException
   {
      started = false;
      LOG.info("PreDestroy");
   }

   @PostConstruct
   public void onStart()
   {
      started = true;
      start();
   }

}
