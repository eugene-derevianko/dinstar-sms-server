package com.symulakr.dinstar.smsserver;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@SpringBootApplication
public class Application
{

   private final static Logger LOG = LogManager.getLogger(Application.class);

   @Autowired
   private SmsServer smsServer;

   public static void main(String[] args) throws IOException
   {
      ApplicationContext context = SpringApplication.run(Application.class, args);
      LOG.info("started");
   }

   @PostConstruct
   public void start()
   {
      LOG.info("PostConstruct");

      smsServer.start();
   }

   @PreDestroy
   public void stop() throws Exception
   {
      try
      {
         smsServer.join(1000);
      }
      catch (InterruptedException e) {
         LOG.error(e);
         throw e;
      }
   }

}
