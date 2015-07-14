package com.symulakr.dinstar.smsserver;

import java.io.BufferedInputStream;
import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.symulakr.dinstar.smsserver.handlers.Handler;
import com.symulakr.dinstar.smsserver.message.Message;
import com.symulakr.dinstar.smsserver.message.body.Body;
import com.symulakr.dinstar.smsserver.message.head.Head;
import com.symulakr.dinstar.smsserver.utils.HeadParser;

@Component
public class SmsServer extends Thread
{

   @Autowired
   private ConnectionSocket connectionSocket;
   @Autowired
   private HandlerFactory handlerFactory;
   @Autowired
   private MessageSender messageSender;

   private final static Logger LOG = LogManager.getLogger(SmsServer.class);

   @Value("${sms.server.port}")
   private int port;

   private boolean started = false;

   @Override
   public void run()
   {
      try
      {
         BufferedInputStream stream = new BufferedInputStream(connectionSocket.getInputStream());
         byte[] bytes = new byte[HeadParser.HEAD_LENGTH];
         while (started)
         {
            if (stream.read(bytes) == HeadParser.HEAD_LENGTH)
            {
               Head head = new Head(bytes);
               byte[] body = new byte[head.getLengthOfBody()];
               if (stream.read(body) == head.getLengthOfBody())
               {
                  Message message = new Message(head, new Body(body));
                  LOG.info("Receive Message: {}", message);
                  Handler handler = handlerFactory.getHandler(head.getMessageType());
                  Message outgoingMessage = handler.processMessage(message);
                  if (outgoingMessage != null)
                  {
                     messageSender.sendMessage(outgoingMessage);
                  }
               }
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
