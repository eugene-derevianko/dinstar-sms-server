package com.symulakr.dinstar.smsserver;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.symulakr.dinstar.smsserver.message.Message;

@Component
public class MessageSender
{

   @Autowired
   private ConnectionSocket connectionSocket;

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

}
