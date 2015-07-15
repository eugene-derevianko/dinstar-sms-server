package com.symulakr.dinstar.smsserver.configuration;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.symulakr.dinstar.smsserver.HandlerFactory;
import com.symulakr.dinstar.smsserver.SmsServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration
{

   @Value("${sms.server.port}")
   private int port;

//   @Bean
//   public Socket socket() throws IOException
//   {
//      return new ServerSocket(port-1).accept();
//   }


}
