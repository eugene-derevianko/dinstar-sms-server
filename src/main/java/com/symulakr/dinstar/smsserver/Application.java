package com.symulakr.dinstar.smsserver;

import org.apache.commons.daemon.Daemon;
import org.apache.commons.daemon.DaemonContext;
import org.apache.commons.daemon.DaemonInitException;

public class Application implements Daemon
{

   private SmsServer smsServer;
   private boolean stopped = false;

   public void init(DaemonContext daemonContext) throws DaemonInitException, Exception
   {
      String[] args = daemonContext.getArguments();

      //todo add application context, get smsServer from one.
      smsServer = new SmsServer(this);
   }

   public void start() throws Exception
   {
      smsServer.start();
   }

   public void stop() throws Exception
   {
      stopped = true;
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

   public boolean isStopped()
   {
      return stopped;
   }

   public void setStopped(boolean stopped)
   {
      this.stopped = stopped;
   }

   public void destroy()
   {
      smsServer = null;
   }
}
