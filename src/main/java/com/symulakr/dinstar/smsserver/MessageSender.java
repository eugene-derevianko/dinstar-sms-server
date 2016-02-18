package com.symulakr.dinstar.smsserver;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.symulakr.dinstar.smsserver.message.Message;

@Component
public class MessageSender
{

   @Autowired
   private SocketChannel socketChannel;

   private final static Logger LOG = LogManager.getLogger(MessageSender.class);

   public void sendMessage(Message message)
   {
      LOG.info("Send message\n{}", message);
      try
      {
         socketChannel.write(ByteBuffer.wrap(message.toBytes()));
      }
      catch (IOException ex)
      {
         LOG.error(ex);
      }
   }

}
