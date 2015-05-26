package com.symulakr.dinstar.smsserver.configuration;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.symulakr.dinstar.smsserver.IncomingQueue;

@Configuration
public class ApplicationConfiguration
{

   @Value("${sms.server.port}")
   private int port;

   @Bean
   public Socket getSocket() throws IOException
   {
      return new ServerSocket(port).accept();
   }

   @Bean
   public IncomingQueue incomingQueue()
   {
      return new IncomingQueue();
   }


}
