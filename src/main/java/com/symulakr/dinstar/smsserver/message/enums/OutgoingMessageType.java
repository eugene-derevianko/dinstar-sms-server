package com.symulakr.dinstar.smsserver.message.enums;

public enum OutgoingMessageType implements MessageType
{

   X00(0x00, "Keepalive message", Direction.ANY, IncomingMessageType.X00),
   X01(0x01, "Send SMS Request", IncomingMessageType.X02),
   // ...
   X06(0x06, "Response to Receive SMS Message", IncomingMessageType.X05),
   X08(0x08, "Status Response", IncomingMessageType.X07),
   X09(0x09, "Send USSD Request", IncomingMessageType.X0A),

   // ...
   X0C(0x0C, "Response to Receive USSD Message", IncomingMessageType.X0B),
   X0E(0x0E, "Response to Send Csq Rssi", IncomingMessageType.X0D),
   X10(0x10, "Response to Send userID and Password Authentication", IncomingMessageType.X0F),
   // ...
   X0111(0x0111, "Receive PORT AND SLOT COUNT", IncomingMessageType.X0112),
   DEFAULT(Short.MIN_VALUE, "Unknown message type", IncomingMessageType.DEFAULT);
   /*
         0x03 Send SMS Result DWG SMS Server
         0x04 Response to Send SMS Result SMS Server DWG
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
   private final short TYPE;
   private final String FUNCTION;
   private final Direction DIRECTION;
   private final MessageType CORRESPONDING_TYPE;

   OutgoingMessageType(int type, String function, Direction direction, MessageType correspondingType)
   {
      TYPE = (short) type;
      FUNCTION = function;
      DIRECTION = direction;
      CORRESPONDING_TYPE = correspondingType;
   }

   OutgoingMessageType(int type, String function, MessageType correspondingType)
   {
      this(type, function, Direction.TO_DWG, correspondingType);
   }

   public short getType()
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

   @Override
   public MessageType getCorrespondingType()
   {
      return CORRESPONDING_TYPE;
   }

   @Override
   public String toString()
   {
      return String.format("%04x - ", TYPE) + getFunction();
   }

}
