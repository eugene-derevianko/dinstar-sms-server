package com.symulakr.dinstar.smsserver;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application
{

   private  SmsServer smsServer;
   private boolean stopped = false;

   public static void main(String[] args) throws IOException
   {
      ApplicationContext context = SpringApplication.run(Application.class, args);
      new SmsServer().start();
   }

}
