package com.symulakr.dinstar.smsserver.message;

@Deprecated
public enum MessageType
{
     X00 (0x00, "Keepalive message", Direction.ANY),
     X01 (0x01, "Send SMS Request", Direction.TO_DWG),

     X0D (0x0D, "Send Csq Rssi", Direction.TO_SERVER),


   //...


   X0F (0x0F, "Send userID and Password Authentication", Direction.ANY),
   X10 (0x10, "Response to Send userID and Password Authentication", Direction.TO_DWG);

   //...

   /*

   0x02 Response to Send SMS Request DWG  SMS Server
   0x03 Send SMS Result DWG SMS Server
   0x04 Response to Send SMS Result SMS Server DWG
   0x05 Receive SMS Message DWG SMS Server
   0x06 Response to Receive SMS Message SMS Server DWG
   0x07 Send SMS Request DWG SMS Server
   0x08 Response to Send SMS Request SMS Server DWG
   0x09 Send USSD Request SMS Server  DWG
   0x0A Response to Send USSD Request DWG  SMS Server
   0x0B Receive USSD Message DWG  SMS Server
   0x0C Response to Receive USSD Message SMS Server  DWG
*   0x0D Send Csq Rssi DWG SMS Server
   0x0E Response to Send Csq Rssi SMS Server DWG
*   0x0F Send userID and Password Authentication DWG SMS Server
*   0x10 Response to Send userID and Password Authentication SMS Server DWG
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
   0x0111 Receive PORT AND SLOT COUNT API Server DWG
   0x0112 Send PORT AND SLOT COUNT DWG API Server

     */
   private final int TYPE;
   private final String FUNCTION;
   private final Direction DIRECTION;

   MessageType(int TYPE, String FUNCTION, Direction DIRECTION)
   {
      this.TYPE = TYPE;
      this.FUNCTION = FUNCTION;
      this.DIRECTION = DIRECTION;
   }

   public int getType()
   {
      return TYPE;
   }

   public String getFunction()
   {
      return FUNCTION;
   }

   public Direction getDirection()
   {
      return DIRECTION;
   }

   @Override public String toString()
   {
      return String.format("%04x - ", TYPE) + getFunction();
   }

   public static enum Direction
   {
      TO_DWG,
      TO_SERVER,
      ANY
   }
}



