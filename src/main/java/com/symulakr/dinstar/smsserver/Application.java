package com.symulakr.dinstar.smsserver;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@SpringBootApplication
public class Application
{

   @Autowired
   private SmsServer smsServer;

   public static void main(String[] args) throws IOException
   {
      ApplicationContext context = SpringApplication.run(Application.class, args);
   }

   @PostConstruct
   public void start()
   {
      smsServer.start();
   }

   @PreDestroy
   public void stop() throws Exception
   {
      try
      {
         smsServer.join(1000);
      }
      catch (InterruptedException e)
      {
         System.err.println(e.getMessage());
         throw e;
      }
   }

}
