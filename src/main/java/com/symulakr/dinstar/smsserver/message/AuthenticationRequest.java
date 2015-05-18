package com.symulakr.dinstar.smsserver.message;

import java.util.Arrays;

public class AuthenticationRequest extends AbstractMessage
{

   public static final int TYPE = 0x0F;

   private String userId;
   private String password;

   public AuthenticationRequest(Head head)
   {
      super(head);
   }

   public String getUserId()
   {
      return userId;
   }

   public String getPassword()
   {
      return password;
   }

   @Override
   protected void parseBody()
   {
      this.userId = new String(Arrays.copyOfRange(body, 0, 16));
      this.password = new String(Arrays.copyOfRange(body, 16, 32));
   }

   @Override
   public int getType()
   {
      return TYPE;
   }

}
