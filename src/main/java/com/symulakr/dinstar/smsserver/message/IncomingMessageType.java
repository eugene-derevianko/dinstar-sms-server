package com.symulakr.dinstar.smsserver.message;

public enum IncomingMessageType implements MessageType
{
   X00(0x00, "Keepalive message", Direction.ANY, OutgoingMessageType.X00),
   X02(0x02, "Response to Send SMS Request", OutgoingMessageType.X01),
   // ...
   X05(0x05, "Receive SMS Message", OutgoingMessageType.X06),
   X07(0x07, "Status Report", OutgoingMessageType.X08),
   // ...
   X0D(0x0D, "Send Csq Rssi", OutgoingMessageType.X0E),
   X0F(0x0F, "Send userID and Password Authentication", OutgoingMessageType.X10),
   // ...
   X0112(0x0112, "Send PORT AND SLOT COUNT", OutgoingMessageType.X0111),
   DEFAULT(Short.MIN_VALUE, "Unknown message type", OutgoingMessageType.DEFAULT);
   
   /*
         0x03 Send SMS Result DWG SMS Server
         0x04 Response to Send SMS Result SMS Server DWG
         0x09 Send USSD Request SMS Server  DWG
         0x0A Response to Send USSD Request DWG  SMS Server
         0x0B Receive USSD Message DWG  SMS Server
         0x0C Response to Receive USSD Message SMS Server  DWG
         
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

   IncomingMessageType(int type, String function, Direction direction, MessageType correspondingType)
   {
      TYPE = (short) type;
      FUNCTION = function;
      DIRECTION = direction;
      CORRESPONDING_TYPE = correspondingType;
   }

   IncomingMessageType(int type, String function, MessageType correspondingType)
   {
      this(type, function, Direction.TO_SERVER, correspondingType);
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

   public static IncomingMessageType fromTypeCode(short type)
   {
      for (IncomingMessageType messageType : values())
      {
         if (messageType.getType() == type)
         {
            return messageType;
         }
      }
      return DEFAULT;
   }

}
