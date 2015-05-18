package com.symulakr.dinstar.smsserver.message;

import java.util.Arrays;

public class AuthenticationRequest extends AbstractMessage
{

   private String userId;
   private String password;

   private byte[] body;

   public AuthenticationRequest(MessageId messageId)
   {
      super(messageId);
   }

   public String getUserId()
   {
      return userId;
   }

   public String getPassword()
   {
      return password;
   }

   public void setBody(byte[] body)
   {
      this.body = body;
      this.userId = new String(Arrays.copyOfRange(body, 0, 16));
      this.password = new String(Arrays.copyOfRange(body, 16, 32));
   }

   @Override
   public byte[] getBody()
   {
      return body;
   }

   @Override
   public int getType()
   {
      return 0x0F;
   }

}
