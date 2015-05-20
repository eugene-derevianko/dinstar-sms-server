package com.symulakr.dinstar.smsserver.message.enums;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import com.symulakr.dinstar.smsserver.charset.Gsm7BitCharsetProvider;

public enum Encoding implements AsByte
{

   GSM_7BIT(0, new Gsm7BitCharsetProvider().charsetForName("X-Gsm7Bit")),
   UNICODE(1, StandardCharsets.UTF_16);

   private final int code;
   private final Charset charset;

   Encoding(int code, Charset charset)
   {
      this.code = code;
      this.charset = charset;
   }

   @Override
   public byte asByte()
   {
      return (byte) code;
   }

   public Charset getCharset()
   {
      return charset;
   }
}