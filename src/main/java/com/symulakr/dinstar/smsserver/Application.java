package com.symulakr.dinstar.smsserver;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application
{
   private final static Logger LOG = LogManager.getLogger(SmsServer.class);


   public static void main(String[] args) throws IOException
   {
      ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
      LOG.info(context.getBeanDefinitionNames());
   }

}

