package com.symulakr.dinstar.smsserver.message.body;

import static com.symulakr.dinstar.smsserver.utils.ArrayUtils.skip0x00;
import static com.symulakr.dinstar.smsserver.utils.EnumUtils.fromByte;

import java.nio.ByteBuffer;

import com.symulakr.dinstar.smsserver.common.ToBytes;
import com.symulakr.dinstar.smsserver.message.enums.SmsResult;

public class SendSmsResult extends BodyFromDWG
{

   private byte countOfNumber;
   private String number;
   private byte port;
   private SmsResult smsResult;
   private byte countOfSlice;
   private byte succeededSlice;

   public SendSmsResult(ToBytes body)
   {
      super(body);
   }

   @Override
   protected void parseByteArray(byte[] body)
   {
      ByteBuffer buffer = ByteBuffer.wrap(body);
      countOfNumber = buffer.get();
      byte[] numberBytes = new byte[24];
      buffer.get(numberBytes);
      number = new String(skip0x00(numberBytes));
      port = buffer.get();
      smsResult = fromByte(buffer.get(), SmsResult.values());
      countOfSlice = buffer.get();
      succeededSlice = buffer.get();
   }

   @Override public String toString()
   {
      return "SendSmsResult{" +
            "countOfNumber=" + countOfNumber +
            ", number='" + number + '\'' +
            ", port=" + port +
            ", smsResult=" + smsResult +
            ", countOfSlice=" + countOfSlice +
            ", succeededSlice=" + succeededSlice +
            '}';
   }
}
