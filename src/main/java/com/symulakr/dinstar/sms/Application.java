package com.symulakr.dinstar.sms;

import org.apache.commons.daemon.Daemon;
import org.apache.commons.daemon.DaemonContext;
import org.apache.commons.daemon.DaemonInitException;

public class Application implements Daemon
{

   private Thread myThread;
   private boolean stopped = false;
   private boolean lastOneWasATick = false;

   public void init(DaemonContext daemonContext) throws DaemonInitException, Exception
   {
      String[] args = daemonContext.getArguments();
      myThread = new Thread()
      {
         private long lastTick = 0;

         @Override
         public synchronized void start()
         {
            Application.this.stopped = false;
            super.start();
         }

         @Override
         public void run()
         {
            while (!stopped)
            {
               long now = System.currentTimeMillis();
               if (now - lastTick >= 1000)
               {
                  System.out.println(!lastOneWasATick ? "tick" : "tock");
                  lastOneWasATick = !lastOneWasATick;
                  lastTick = now;
               }
            }
         }
      };
   }

   public void start() throws Exception
   {
      myThread.start();
   }

   public void stop() throws Exception
   {
      stopped = true;
      try
      {
         myThread.join(1000);
      }
      catch (InterruptedException e)
      {
         System.err.println(e.getMessage());
         throw e;
      }
   }

   public void destroy()
   {
      myThread = null;
   }
}
