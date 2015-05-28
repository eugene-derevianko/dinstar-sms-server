package com.symulakr.dinstar.smsserver.message.body;

import static com.symulakr.dinstar.smsserver.utils.EnumUtils.fromByte;

import java.nio.ByteBuffer;

import com.symulakr.dinstar.smsserver.common.ToBytes;
import com.symulakr.dinstar.smsserver.message.enums.Encoding;
import com.symulakr.dinstar.smsserver.message.enums.UssdStatus;

public class UssdMessage extends BodyFromDWG
{

   private byte port;
   private UssdStatus ussdStatus;
   private Encoding encoding;
   private short contentLength;
   private String content;

   public UssdMessage(ToBytes body)
   {
      super(body);
   }

   @Override
   protected void parseByteArray(byte[] body)
   {
      ByteBuffer buffer = ByteBuffer.wrap(body);
      port = buffer.get();
      ussdStatus = fromByte(buffer.get(), UssdStatus.values());
      contentLength = buffer.getShort();
      encoding = fromByte(buffer.get(), Encoding.values());
      byte[] contentBytes = new byte[contentLength];
      buffer.get(contentBytes);
      content = new String(contentBytes, encoding.getCharset());
   }

   @Override
   public String toString()
   {
      return new StringBuilder(super.toString())
            .append("\n\tPort: ")
            .append(port)
            .append("\n\tUssd Status: ")
            .append(ussdStatus)
            .append("\n\tEncoding: ")
            .append(encoding)
            .append("\n\tMessage length: ")
            .append(contentLength)
            .append("\n\tMessage: ")
            .append(content)
            .toString();
   }

   public byte getPort()
   {
      return port;
   }

   public UssdStatus getUssdStatus()
   {
      return ussdStatus;
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
