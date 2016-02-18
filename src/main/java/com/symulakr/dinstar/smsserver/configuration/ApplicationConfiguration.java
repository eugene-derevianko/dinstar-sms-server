package com.symulakr.dinstar.smsserver.configuration;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration
{

   @Value("${sms.server.port}")
   private int port;

   @Bean
   public SocketChannel socket() throws IOException
   {
      ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
      serverSocketChannel.socket()
            .bind(new InetSocketAddress(port));
      return serverSocketChannel.accept();
   }

}
