package com.symulakr.dinstar.smsserver.message.enums;

import java.nio.ByteBuffer;

import com.symulakr.dinstar.smsserver.common.ToBytes;

public interface MessageType extends ToBytes
{

   short getType();

   String getFunction();

   Direction getDirection();

   MessageType getCorrespondingType();

   @Override
   default byte[] toBytes()
   {
      return ByteBuffer.allocate(2)
            .putShort(getType())
            .array();
   }

   enum Direction
   {
      TO_DWG,
      TO_SERVER,
      ANY
   }

}
