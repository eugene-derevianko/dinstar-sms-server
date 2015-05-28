package com.symulakr.dinstar.smsserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConnectionSocket
{

   @Autowired
   private Socket socket;

   public InputStream getInputStream() throws IOException
   {
      return socket.getInputStream();
   }

   public OutputStream getOutputStream() throws IOException
   {
      return socket.getOutputStream();
   }

   @PreDestroy
   public void onStop() throws IOException
   {
      socket.close();
   }

}
