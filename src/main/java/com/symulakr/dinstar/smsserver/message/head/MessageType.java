package com.symulakr.dinstar.smsserver.message.head;

import java.nio.ByteBuffer;

import com.symulakr.dinstar.smsserver.common.ToBytes;

public enum MessageType implements ToBytes
{

   KEEPALIVE_MESSAGE(0x0000, "Keepalive message"),
   SEND_SMS_REQUEST(0x0001, "Send SMS Request"),
   RESPONSE_TO_SEND_SMS_REQUEST(0x0002, "Response to Send SMS Request"),
   SEND_SMS_RESULT(0x0003, "Send SMS Result"),
   RESPONSE_TO_SEND_SMS_RESULT(0x0004, "Response to Send SMS Result"),
   RECEIVE_SMS_MESSAGE(0x0005, "Receive SMS Message"),
   RESPONSE_TO_RECEIVE_SMS_MESSAGE(0x0006, "Response to Receive SMS Message"),
   STATUS_REPORT(0x0007, "Status Report"),
   RESPONSE_TO_STATUS_REPORT(0x0008, "Status Response"),
   SEND_USSD_REQUEST(0x0009, "Send USSD Request"),
   RESPONSE_TO_SEND_USSD_REQUEST(0x0A, "Response to Send USSD Request"),
   RECEIVE_USSD_MESSAGE(0x000B, "Receive USSD Message"),
   RESPONSE_TO_RECEIVE_USSD_MESSAGE(0x000C, "Response to Receive USSD Message"),
   SEND_CSQ_RSSI(0x000D, "Send Csq Rssi"),
   RESPONSE_TO_SEND_CSQ_RSSI(0x000E, "Response to Send Csq Rssi"),
   SEND_AUTHENTICATION(0x000F, "Send userID and Password Authentication"),
   RESPONSE_TO_SEND_AUTHENTICATION(0x0010, "Response to Send userID and Password Authentication"),
   RECEIVE_SMS_RECEIPT(0x0011, "Receive SMS Receipt"),
   RESPONSE_TO_RECEIVE_SMS_RECEIPT(0x0012, "Response to Receive SMS Receipt"),
   // ...
   X0111(0x0111, "Receive PORT AND SLOT COUNT"),
   X0112(0x0112, "Send PORT AND SLOT COUNT"),
   DEFAULT(Short.MIN_VALUE, "Unknown message type");

   private short code;
   private String description;

   MessageType(int code, String description)
   {
      this.code = (short) code;
      this.description = description;
   }

   @Override
   public byte[] toBytes()
   {
      return ByteBuffer.allocate(2)
            .putShort(code)
            .array();
   }

   public String getDescription()
   {
      return description;
   }

   public static MessageType decode(short code)
   {
      for (MessageType type : values())
      {
         if (type.code == code)
         {
            return type;
         }
      }
      return DEFAULT;
   }

   public static MessageType fromBytes(byte[] bytes)
   {
      return decode(ByteBuffer.wrap(bytes)
            .getShort());
   }
}
