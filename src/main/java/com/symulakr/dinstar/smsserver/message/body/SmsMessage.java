package com.symulakr.dinstar.smsserver.message.body;

import static com.symulakr.dinstar.smsserver.utils.ArrayUtils.skip0x00;
import static com.symulakr.dinstar.smsserver.utils.EnumUtils.fromByte;

import java.nio.ByteBuffer;

import com.symulakr.dinstar.smsserver.common.ToBytes;
import com.symulakr.dinstar.smsserver.message.enums.ContentType;
import com.symulakr.dinstar.smsserver.message.enums.Encoding;

public class SmsMessage extends BodyFromDWG
{
   private String number;
   private ContentType contentType;
   private byte port;
   private String timestamp;
   private byte timeZone;
   private Encoding encoding;
   private short contentLength;
   private String content;

   public SmsMessage(ToBytes body)
   {
      super(body);
   }

   @Override
   protected void parseByteArray(byte[] body)
   {
      ByteBuffer buffer = ByteBuffer.wrap(body);
      byte[] numberBytes = new byte[24];
      buffer.get(numberBytes);
      number = new String(skip0x00(numberBytes));
      contentType = fromByte(buffer.get(), ContentType.values());
      port = buffer.get();
      byte[] time = new byte[15];
      buffer.get(time);
      timestamp = new String(skip0x00(time));
      timeZone = buffer.get();
      encoding = fromByte(buffer.get(), Encoding.values());
      contentLength = buffer.getShort();
      byte[] contentBytes = new byte[contentLength];
      buffer.get(contentBytes);
      content = new String(contentBytes, encoding.getCharset());
   }

   @Override
   public String toString()
   {
      return new StringBuilder(super.toString())
            .append("\n\tNumber: ")
            .append(number)
            .append("\n\tMessage Type: ")
            .append(contentType)
            .append("\n\ttimestamp: ")
            .append(timestamp)
            .append("\n\tEncoding: ")
            .append(encoding)
            .append("\n\tMessage length: ")
            .append(contentLength)
            .append("\n\tMessage: ")
            .append(content)
            .toString();
   }

   public String getNumber()
   {
      return number;
   }

   public ContentType getContentType()
   {
      return contentType;
   }

   public byte getPort()
   {
      return port;
   }

   public String getTimestamp()
   {
      return timestamp;
   }

   public byte getTimeZone()
   {
      return timeZone;
   }

   public Encoding getEncoding()
   {
      return encoding;
   }

   public short getContentLength()
   {
      return contentLength;
   }

   public String getContent()
   {
      return content;
   }

}
