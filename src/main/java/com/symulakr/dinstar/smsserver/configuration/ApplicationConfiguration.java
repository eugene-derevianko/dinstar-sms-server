package com.symulakr.dinstar.smsserver.configuration;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ApplicationConfiguration
{

   @Value("${sms.server.port}")
   private int port;

   @Bean
   @Scope
   public ServerSocketChannel socket() throws IOException
   {
      ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
      serverSocketChannel.socket()
            .bind(new InetSocketAddress(port));
      return serverSocketChannel;
   }

}
