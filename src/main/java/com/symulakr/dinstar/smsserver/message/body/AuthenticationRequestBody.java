package com.symulakr.dinstar.smsserver.message.body;

import static com.symulakr.dinstar.smsserver.utils.ArrayUtils.skip0x00;

import java.util.Arrays;

import com.symulakr.dinstar.smsserver.common.ToBytes;

public class AuthenticationRequestBody extends BodyFromDWG
{

   private String userId;
   private String password;

   public AuthenticationRequestBody(ToBytes body)
   {
      super(body);
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
   protected void parseByteArray(byte[] body)
   {
      this.userId = new String(skip0x00(Arrays.copyOfRange(body, 0, 16)));
      this.password = new String(skip0x00(Arrays.copyOfRange(body, 16, 32)));
   }

   @Override
   public String toString()
   {
      return new StringBuilder(super.toString())
            .append("\n\tuserId: ")
            .append(userId)
            .append("\n\tpassword: ")
            .append(password)
            .toString();
   }

}
