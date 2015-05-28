package com.symulakr.dinstar.smsserver.message.body;

import java.nio.ByteBuffer;

import com.symulakr.dinstar.smsserver.common.ToBytes;
import com.symulakr.dinstar.smsserver.message.enums.ContentType;
import com.symulakr.dinstar.smsserver.message.enums.Encoding;

public class OutSmsMessage implements ToBytes
{
   private byte[] bytes;

   private byte port;
   private Encoding encoding;
   private ContentType contentType;
   private byte countOfNumber;
   private String number;
   private short contentLength;
   private String content;

   public OutSmsMessage(String number, String content)
   {
      this.number = number;
      this.content = content;
      this.port = 1;
      this.encoding = Encoding.UNICODE;
      this.contentType = ContentType.SMS;
      this.countOfNumber = 1;
      byte[] contentBytes = content.getBytes(encoding.getCharset());
      this.contentLength = (short) (2 * contentBytes.length);

      ByteBuffer byteBuffer = ByteBuffer.allocate(30 + contentLength);
      byteBuffer.put(port);
      byteBuffer.put(encoding.asByte());
      byteBuffer.put(contentType.asByte());
      byteBuffer.put(countOfNumber);
      byteBuffer.put(numberToBytes(number));
      byteBuffer.putShort(contentLength);
      byteBuffer.put(contentBytes);

      this.bytes = byteBuffer.array();
   }

   @Override
   public byte[] toBytes()
   {
      return bytes;
   }

   public int bodyLength()
   {
      return bytes.length;
   }

   private byte[] numberToBytes(String number)
   {
      byte[] bytes = new byte[24];
      for (int i = 0; i < number.length(); i++)
      {
         bytes[i] = (byte) number.charAt(i);
      }
      return bytes;
   }

   public void setNumber(String number)
   {
      this.number = number;
   }

   public void setContentType(ContentType contentType)
   {
      this.contentType = contentType;
   }

   public void setPort(byte port)
   {
      this.port = port;
   }

   public void setEncoding(Encoding encoding)
   {
      this.encoding = encoding;
   }

   public void setContentLength(short contentLength)
   {
      this.contentLength = contentLength;
   }

   public void setContent(String content)
   {
      this.content = content;
   }
}
