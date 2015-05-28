package com.symulakr.dinstar.smsserver.message.enums;

public enum SmsResult implements AsByte
{

   SUCCEED(0, "Succeed"),
   FAIL(1, "Fail"),
   TIMEOUT(2, "Timeout"),
   BAD_REQUEST(3, "Bad request"),
   PORT_UNAVAILABLE(4, "Port unavailable"),
   PARTIAL_SUCCEED(5, "Partial succeed"),
   OTHER_ERROR(0xFF, "other error"), ;

   private int code;
   private String description;

   SmsResult(int code, String description)
   {
      this.code = code;
      this.description = description;
   }

   @Override
   public byte asByte()
   {
      return (byte) code;
   }

   public String getDescription()
   {
      return description;
   }

}
