package com.symulakr.dinstar.smsserver;

import com.symulakr.dinstar.smsserver.handlers.NullResponseHandler;
import com.symulakr.dinstar.smsserver.handlers.SendSmsResultHandler;
import com.symulakr.dinstar.smsserver.handlers.SmsMessageHandler;
import com.symulakr.dinstar.smsserver.handlers.UssdMessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.symulakr.dinstar.smsserver.handlers.AuthenticationHandler;
import com.symulakr.dinstar.smsserver.handlers.CsqRssiHandler;
import com.symulakr.dinstar.smsserver.handlers.EmptyResponseHandler;
import com.symulakr.dinstar.smsserver.handlers.Handler;
import com.symulakr.dinstar.smsserver.handlers.PortStatusHandler;
import com.symulakr.dinstar.smsserver.message.head.MessageType;

@Component
public class HandlerFactory
{
   @Autowired
   private SendSmsResultHandler sendSmsResultHandler;
   @Autowired
   private PortStatusHandler portStatusHandler;
   @Autowired
   private CsqRssiHandler csqRssiHandler;
   @Autowired
   private AuthenticationHandler authenticationHandler;
//   @Autowired
   private SmsMessageHandler smsMessageHandler;
   @Autowired
   private UssdMessageHandler ussdMessageHandler;
   @Autowired
   private NullResponseHandler nullResponseHandler;
   @Autowired
   private EmptyResponseHandler emptyResponseHandler;

   public Handler getHandler(MessageType messageType)
   {
      switch (messageType)
      {
         case X00:
            break;
         case X01:
            break;
         case X02:
            return nullResponseHandler;
         case X03:
            return sendSmsResultHandler;
         case X04:
            break;
         case X05:
            return smsMessageHandler;
         case X06:
            break;
         case X07:
            return portStatusHandler;
         case X08:
            break;
         case X09:
            break;
         case X0A:
            break;
         case X0B:
            return ussdMessageHandler;
         case X0C:
            break;
         case X0D:
            return csqRssiHandler;
         case X0E:
            break;
         case X0F:
            return authenticationHandler;
         case X10:
            break;
         case X0111:
            break;
         case X0112:
            break;
         case DEFAULT:
            break;
      }
      return emptyResponseHandler;
   }

}
