package com.symulakr.dinstar.smsserver.handlers;

import com.symulakr.dinstar.smsserver.message.Message;
import com.symulakr.dinstar.smsserver.message.body.ResponseBody;
import com.symulakr.dinstar.smsserver.message.body.SimpleResponseBody;

public abstract class SimpleResponseHandler extends Handler
{

   @Override
   protected ResponseBody createBody(Message message)
   {
      return new SimpleResponseBody(isOk(message));
   }

   protected abstract boolean isOk(Message message);
}
