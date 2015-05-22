package com.symulakr.dinstar.smsserver;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application
{

   public static void main(String[] args) throws IOException
   {
      ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
   }

}

