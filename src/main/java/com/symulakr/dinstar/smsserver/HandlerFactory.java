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
   @Autowired
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
         case KEEPALIVE_MESSAGE:
            break;
         case SEND_SMS_REQUEST:
            break;
         case RESPONSE_TO_SEND_SMS_REQUEST:
            return nullResponseHandler;
         case SEND_SMS_RESULT:
            return sendSmsResultHandler;
         case RESPONSE_TO_SEND_SMS_RESULT:
            break;
         case RECEIVE_SMS_MESSAGE:
            return smsMessageHandler;
         case RESPONSE_TO_RECEIVE_SMS_MESSAGE:
            break;
         case STATUS_REPORT:
            return portStatusHandler;
         case RESPONSE_TO_STATUS_REPORT:
            break;
         case SEND_USSD_REQUEST:
            break;
         case RESPONSE_TO_SEND_USSD_REQUEST:
            break;
         case RECEIVE_USSD_MESSAGE:
            return ussdMessageHandler;
         case RESPONSE_TO_RECEIVE_USSD_MESSAGE:
            break;
         case SEND_CSQ_RSSI:
            return csqRssiHandler;
         case RESPONSE_TO_SEND_CSQ_RSSI:
            break;
         case SEND_AUTHENTICATION:
            return authenticationHandler;
         case RESPONSE_TO_SEND_AUTHENTICATION:
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
