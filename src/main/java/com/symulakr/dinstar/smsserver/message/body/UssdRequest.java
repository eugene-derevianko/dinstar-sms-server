package com.symulakr.dinstar.smsserver.message.body;

import java.nio.ByteBuffer;

import com.symulakr.dinstar.smsserver.common.ToBytes;
import com.symulakr.dinstar.smsserver.message.enums.Encoding;
import com.symulakr.dinstar.smsserver.message.enums.UssdType;

public class UssdRequest implements ToBytes
{

   private static Encoding encoding = Encoding.ASCII;

   private byte[] bytes;
   private byte port;
   private UssdType ussdType;
   private short contentLength;
   private String content;

   public UssdRequest(String content)
   {
      this(UssdType.SEND, content);
   }

   public UssdRequest(UssdType ussdType, String content)
   {
      this((byte) 0, ussdType, content);
   }

   public UssdRequest(byte port, UssdType ussdType, String content)
   {
      this.port = port;
      this.ussdType = ussdType;
      this.content = content;
      byte[] contentBytes = content.getBytes(encoding.getCharset());
      this.contentLength = (short) (2 * contentBytes.length);

      this.bytes = ByteBuffer.allocate(4 + contentLength)
            .put(port)
            .put(ussdType.asByte())
            .putShort(contentLength)
            .put(contentBytes)
            .array();
   }

   @Override
   public String toString()
   {
      return new StringBuilder(super.toString())
            .append("\n\tPort: ")
            .append(port)
            .append("\n\tUssd Type: ")
            .append(ussdType)
            .append("\n\tMessage length: ")
            .append(contentLength)
            .append("\n\tContent: ")
            .append(content)
            .toString();
   }

   public byte getPort()
   {
      return port;
   }

   public UssdType getUssdType()
   {
      return ussdType;
   }

   public short getContentLength()
   {
      return contentLength;
   }

   public String getContent()
   {
      return content;
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
}
