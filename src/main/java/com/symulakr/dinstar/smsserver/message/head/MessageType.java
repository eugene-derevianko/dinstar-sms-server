package com.symulakr.dinstar.smsserver.message.head;

import java.nio.ByteBuffer;

import com.symulakr.dinstar.smsserver.common.ToBytes;

public enum MessageType implements ToBytes
{
   X00(0x00, "Keepalive message"),
   X01(0x01, "Send SMS Request"),
   X02(0x02, "Response to Send SMS Request"),
   X03(0x03, "Send SMS Result"),
   X04(0x04, "Response to Send SMS Result"),
   X05(0x05, "Receive SMS Message"),
   X06(0x06, "Response to Receive SMS Message"),
   X07(0x07, "Status Report"),
   X08(0x08, "Status Response"),
   X09(0x09, "Send USSD Request"),
   X0A(0x0A, "Response to Send USSD Request"),
   X0B(0x0B, "Receive USSD Message"),
   X0C(0x0C, "Response to Receive USSD Message"),
   X0D(0x0D, "Send Csq Rssi"),
   X0E(0x0E, "Response to Send Csq Rssi"),
   X0F(0x0F, "Send userID and Password Authentication"),
   X10(0x10, "Response to Send userID and Password Authentication"),
   // ...
   X0111(0x0111, "Receive PORT AND SLOT COUNT"),
   X0112(0x0112, "Send PORT AND SLOT COUNT"),
   DEFAULT(Short.MIN_VALUE, "Unknown message type");

      /*
        *0x03 Send SMS Result DWG SMS Server
        *0x04 Response to Send SMS Result SMS Server DWG
        *0x09 Send USSD Request SMS Server  DWG
        *0x0A Response to Send USSD Request DWG  SMS Server
        *0x0B Receive USSD Message DWG  SMS Server
        *0x0C Response to Receive USSD Message SMS Server  DWG

         0x11 Receive SMS Receipt DWG SMS Server
         0x12 Response to Receive SMS Receipt SMS Server DWG
         0x0101 Receive SIM MESSAGE API Server DWG
         0x0102 Send SIM MESSAGE DWG API Server
         0x0103 Receive Unbind Port API Server DWG
         0x0104 Response to Unbind Port DWG API Server
         0x0105 Receive SWITCH SIMCARD API Server DWG
         0x0106 Response to SWITCH SIMCARD DWG API Server
         0x0107 Receive RESET MODULE API Server DWG
         0x0108 Response to RESET MODULE DWG API Server
         0x0109 Receive RESTART SIMBOX API Server DWG
         0x010A Response to RESTART SIMBOX DWG API Server
         0x010B Receive SIMBOX MESSAGE API Server DWG
         0x010C Send SIMBOX MESSAGE DWG API Server
         0x010D Receive Obtain IMEI API Server DWG
         0x010E Response to Obtain IMEI DWG API Server
         0x010F Receive MODIFY IMEI API Server DWG
         0x0110 Response to MODIFY IMEI DWG API Server
    */

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

   public static MessageType fromBytes(byte[] bytes)
   {
      short sh = ByteBuffer.wrap(bytes)
            .getShort();
      for (MessageType type : values())
      {
         if (type.code == sh)
         {
            return type;
         }
      }
      return DEFAULT;
   }
}
