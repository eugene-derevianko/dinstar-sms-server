package com.symulakr.dinstar.smsserver.charset;

import java.nio.CharBuffer;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import java.util.HashMap;

public class Gsm7BitCharset extends Charset {

   // HashMap's used for encoding and decoding
   protected static HashMap<String, Byte> defaultEncodeMap = new HashMap<String, Byte>();
   protected static HashMap<Byte, String> defaultDecodeMap = new HashMap<Byte, String>();
   protected static HashMap<String, Byte> extEncodeMap = new HashMap<String, Byte>();
   protected static HashMap<Byte, String> extDecodeMap = new HashMap<Byte, String>();

   // Data to populate the hashmaps with
   private static final Object[][] gsmCharacters = {
         { "@",      (byte) 0x00 },
         { "£",      (byte) 0x01 },
         { "$",      (byte) 0x02 },
         { "¥",      (byte) 0x03 },
         { "è",      (byte) 0x04 },
         { "é",      (byte) 0x05 },
         { "ù",      (byte) 0x06 },
         { "ì",      (byte) 0x07 },
         { "ò",      (byte) 0x08 },
         { "Ç",      (byte) 0x09 },
         { "\n",     (byte) 0x0a },
         { "Ø",      (byte) 0x0b },
         { "ø",      (byte) 0x0c },
         { "\r",     (byte) 0x0d },
         { "Å",      (byte) 0x0e },
         { "å",      (byte) 0x0f },
         { "\u0394", (byte) 0x10 },
         { "_",      (byte) 0x11 },
         { "\u03A6", (byte) 0x12 },
         { "\u0393", (byte) 0x13 },
         { "\u039B", (byte) 0x14 },
         { "\u03A9", (byte) 0x15 },
         { "\u03A0", (byte) 0x16 },
         { "\u03A8", (byte) 0x17 },
         { "\u03A3", (byte) 0x18 },
         { "\u0398", (byte) 0x19 },
         { "\u039E", (byte) 0x1a },
         { "\u001B", (byte) 0x1b }, // 27 is Escape character
         { "Æ",      (byte) 0x1c },
         { "æ",      (byte) 0x1d },
         { "ß",      (byte) 0x1e },
         { "É",      (byte) 0x1f },
         { "\u0020", (byte) 0x20 },
         { "!",      (byte) 0x21 },
         { "\"",     (byte) 0x22 },
         { "#",      (byte) 0x23 },
         { "¤",      (byte) 0x24 },
         { "%",      (byte) 0x25 },
         { "&",      (byte) 0x26 },
         { "'",      (byte) 0x27 },
         { "(",      (byte) 0x28 },
         { ")",      (byte) 0x29 },
         { "*",      (byte) 0x2a },
         { "+",      (byte) 0x2b },
         { ",",      (byte) 0x2c },
         { "-",      (byte) 0x2d },
         { ".",      (byte) 0x2e },
         { "/",      (byte) 0x2f },
         { "0",      (byte) 0x30 },
         { "1",      (byte) 0x31 },
         { "2",      (byte) 0x32 },
         { "3",      (byte) 0x33 },
         { "4",      (byte) 0x34 },
         { "5",      (byte) 0x35 },
         { "6",      (byte) 0x36 },
         { "7",      (byte) 0x37 },
         { "8",      (byte) 0x38 },
         { "9",      (byte) 0x39 },
         { ":",      (byte) 0x3a },
         { ";",      (byte) 0x3b },
         { "<",      (byte) 0x3c },
         { "=",      (byte) 0x3d },
         { ">",      (byte) 0x3e },
         { "?",      (byte) 0x3f },
         { "¡",      (byte) 0x40 },
         { "A",      (byte) 0x41 },
         { "B",      (byte) 0x42 },
         { "C",      (byte) 0x43 },
         { "D",      (byte) 0x44 },
         { "E",      (byte) 0x45 },
         { "F",      (byte) 0x46 },
         { "G",      (byte) 0x47 },
         { "H",      (byte) 0x48 },
         { "I",      (byte) 0x49 },
         { "J",      (byte) 0x4a },
         { "K",      (byte) 0x4b },
         { "L",      (byte) 0x4c },
         { "M",      (byte) 0x4d },
         { "N",      (byte) 0x4e },
         { "O",      (byte) 0x4f },
         { "P",      (byte) 0x50 },
         { "Q",      (byte) 0x51 },
         { "R",      (byte) 0x52 },
         { "S",      (byte) 0x53 },
         { "T",      (byte) 0x54 },
         { "U",      (byte) 0x55 },
         { "V",      (byte) 0x56 },
         { "W",      (byte) 0x57 },
         { "X",      (byte) 0x58 },
         { "Y",      (byte) 0x59 },
         { "Z",      (byte) 0x5a },
         { "Ä",      (byte) 0x5b },
         { "Ö",      (byte) 0x5c },
         { "Ñ",      (byte) 0x5d },
         { "Ü",      (byte) 0x5e },
         { "§",      (byte) 0x5f },
         { "¿",      (byte) 0x60 },
         { "a",      (byte) 0x61 },
         { "b",      (byte) 0x62 },
         { "c",      (byte) 0x63 },
         { "d",      (byte) 0x64 },
         { "e",      (byte) 0x65 },
         { "f",      (byte) 0x66 },
         { "g",      (byte) 0x67 },
         { "h",      (byte) 0x68 },
         { "i",      (byte) 0x69 },
         { "j",      (byte) 0x6a },
         { "k",      (byte) 0x6b },
         { "l",      (byte) 0x6c },
         { "m",      (byte) 0x6d },
         { "n",      (byte) 0x6e },
         { "o",      (byte) 0x6f },
         { "p",      (byte) 0x70 },
         { "q",      (byte) 0x71 },
         { "r",      (byte) 0x72 },
         { "s",      (byte) 0x73 },
         { "t",      (byte) 0x74 },
         { "u",      (byte) 0x75 },
         { "v",      (byte) 0x76 },
         { "w",      (byte) 0x77 },
         { "x",      (byte) 0x78 },
         { "y",      (byte) 0x79 },
         { "z",      (byte) 0x7a },
         { "ä",      (byte) 0x7b },
         { "ö",      (byte) 0x7c },
         { "ñ",      (byte) 0x7d },
         { "ü",      (byte) 0x7e },
         { "à",      (byte) 0x7f }
   };

   private static final Object[][] gsmExtensionCharacters = {
         { "\n", (byte) 0x0a },
         { "^",  (byte) 0x14 },
         { " ",  (byte) 0x1b }, // reserved for future extensions
         { "{",  (byte) 0x28 },
         { "}",  (byte) 0x29 },
         { "\\", (byte) 0x2f },
         { "[",  (byte) 0x3c },
         { "~",  (byte) 0x3d },
         { "]",  (byte) 0x3e },
         { "|",  (byte) 0x40 },
         { "",  (byte) 0x65 }
   };

   // static section that populates the encode and decode HashMap objects
   static {
      // default alphabet
      int len = gsmCharacters.length;
      for (int i = 0; i < len; i++) {
         Object[] map = gsmCharacters[i];
         defaultEncodeMap.put((String) map[0], (Byte) map[1]);
         defaultDecodeMap.put((Byte) map[1], (String) map[0]);
      }

      // extended alphabet
      len = gsmExtensionCharacters.length;
      for (int i = 0; i < len; i++) {
         Object[] map = gsmExtensionCharacters[i];
         extEncodeMap.put((String) map[0], (Byte) map[1]);
         extDecodeMap.put((Byte) map[1], (String) map[0]);
      }
   }

   /**
    * Constructor for the Gsm7Bit charset.  Call the superclass
    * constructor to pass along the name(s) we'll be known by.
    * Then save a reference to the delegate Charset.
    */
   protected Gsm7BitCharset(String canonical, String[] aliases) {
      super(canonical, aliases);
   }

   // ----------------------------------------------------------

   /**
    * Called by users of this Charset to obtain an encoder.
    * This implementation instantiates an instance of a private class
    * (defined below) and passes it an encoder from the base Charset.
    */
   public CharsetEncoder newEncoder() {
      return new Gsm7BitEncoder(this);
   }

   /**
    * Called by users of this Charset to obtain a decoder.
    * This implementation instantiates an instance of a private class
    * (defined below) and passes it a decoder from the base Charset.
    */
   public CharsetDecoder newDecoder() {
      return new Gsm7BitDecoder(this);
   }

   /**
    * This method must be implemented by concrete Charsets.  We always
    * say no, which is safe.
    */
   public boolean contains(Charset cs) {
      return (false);
   }

   /**
    * The encoder implementation for the Gsm7Bit Charset.
    * This class, and the matching decoder class below, should also
    * override the "impl" methods, such as implOnMalformedInput() and
    * make passthrough calls to the baseEncoder object.  That is left
    * as an exercise for the hacker.
    */
   private class Gsm7BitEncoder extends CharsetEncoder {

      /**
       * Constructor, call the superclass constructor with the
       * Charset object and the encodings sizes from the
       * delegate encoder.
       */
      Gsm7BitEncoder(Charset cs) {
         super(cs, 1, 2);
      }

      /**
       * Implementation of the encoding loop.
       */
      protected CoderResult encodeLoop(CharBuffer cb, ByteBuffer bb) {
         CoderResult cr = CoderResult.UNDERFLOW;

         while (cb.hasRemaining() && bb.hasRemaining()) {
            char ch = cb.get();

            // first check the default alphabet
            Byte b = (Byte) defaultEncodeMap.get("" + ch);
            if (b != null) {
               bb.put((byte) b.byteValue());
            } else {
               // check extended alphabet
               b = (Byte) extEncodeMap.get("" + ch);
               if (b != null) {
                  // since the extended character set takes two bytes 
                  // we have to check that there is enough space left
                  if (bb.remaining() < 2) {
                     // go back one step
                     cb.position(cb.position() - 1);
                     cr = CoderResult.OVERFLOW;
                     break;
                  }
                  // all ok, add it to the buffer
                  bb.put((byte) 0x1b);
                  bb.put((byte) b.byteValue());
               } else {
                  // no match found, send a ?
                  b = (byte) 0x3F;
                  bb.put((byte) b.byteValue());
               }
            }
         }
         return cr;
      }
   }

   // --------------------------------------------------------

   /**
    * The decoder implementation for the Gsm 7Bit Charset.
    */
   private class Gsm7BitDecoder extends CharsetDecoder {

      /**
       * Constructor, call the superclass constructor with the
       * Charset object and pass alon the chars/byte values
       * from the delegate decoder.
       */
      Gsm7BitDecoder(Charset cs) {
         super(cs, 1, 1);
      }

      /**
       * Implementation of the decoding loop.
       */
      protected CoderResult decodeLoop(ByteBuffer bb, CharBuffer cb) {
         CoderResult cr = CoderResult.UNDERFLOW;

         while (bb.hasRemaining() && cb.hasRemaining()) {
            byte b = bb.get();

            // first check the default alphabet
            String s = (String) defaultDecodeMap.get(new Byte(b));
            if (s != null) {
               char ch = s.charAt(0);
               if (ch != '\u001B') {
                  cb.put(ch);
               } else {
                  // check the extended alphabet
                  if (bb.hasRemaining()) {
                     b = bb.get();
                     s = (String) extDecodeMap.get(new Byte(b));
                     if (s != null) {
                        ch = s.charAt(0);
                        cb.put(ch);
                     } else {
                        cb.put('?');
                     }
                  }
               }
            } else {
               cb.put('?');
            }
         }
         return cr;
      }
   }

   /*
      { "\u0040", new Byte((byte) 0x00) },   // COMMERCIAL AT
      { "\u00A3", new Byte((byte) 0x01) },   // POUND SIGN
      { "\u0024", new Byte((byte) 0x02) },   // DOLLAR SIGN
      { "\u00A5", new Byte((byte) 0x03) },   // YEN SIGN
      { "\u00E8", new Byte((byte) 0x04) },   // LATIN SMALL LETTER E WITH GRAVE
      { "\u00E9", new Byte((byte) 0x05) },   // LATIN SMALL LETTER E WITH ACUTE
      { "\u00F9", new Byte((byte) 0x06) },   // LATIN SMALL LETTER U WITH GRAVE
      { "\u00EC", new Byte((byte) 0x07) },   // LATIN SMALL LETTER I WITH GRAVE
      { "\u00F2", new Byte((byte) 0x08) },   // LATIN SMALL LETTER O WITH GRAVE
      { "\u00E7", new Byte((byte) 0x09) },   // LATIN SMALL LETTER C WITH CEDILLA
      { "\\u000A", new Byte((byte) 0x0A) },   // LINE FEED
      { "\u00D8", new Byte((byte) 0x0B) },   // LATIN CAPITAL LETTER O WITH STROKE
      { "\u00F8", new Byte((byte) 0x0C) },   // LATIN SMALL LETTER O WITH STROKE
      { "\\u000D", new Byte((byte) 0x0D) },   // CARRIAGE RETURN
      { "\u00C5", new Byte((byte) 0x0E) },   // LATIN CAPITAL LETTER A WITH RING ABOVE
      { "\u00E5", new Byte((byte) 0x0F) },   // LATIN SMALL LETTER A WITH RING ABOVE
      { "\u0394", new Byte((byte) 0x10) },   // GREEK CAPITAL LETTER DELTA
      { "\u005F", new Byte((byte) 0x11) },   // LOW LINE
      { "\u03A6", new Byte((byte) 0x12) },   // GREEK CAPITAL LETTER PHI
      { "\u0393", new Byte((byte) 0x13) },   // GREEK CAPITAL LETTER GAMMA
      { "\u039B", new Byte((byte) 0x14) },   // GREEK CAPITAL LETTER LAMDA
      { "\u03A9", new Byte((byte) 0x15) },   // GREEK CAPITAL LETTER OMEGA
      { "\u03A0", new Byte((byte) 0x16) },   // GREEK CAPITAL LETTER PI
      { "\u03A8", new Byte((byte) 0x17) },   // GREEK CAPITAL LETTER PSI
      { "\u03A3", new Byte((byte) 0x18) },   // GREEK CAPITAL LETTER SIGMA
      { "\u0398", new Byte((byte) 0x19) },   // GREEK CAPITAL LETTER THETA
      { "\u039E", new Byte((byte) 0x1A) },   // GREEK CAPITAL LETTER XI
      { "\u00A0", new Byte((byte) 0x1B) },   // ESCAPE TO EXTENSION TABLE (or displayed as NBSP, see note above)
      { "\u00C6", new Byte((byte) 0x1C) },   // LATIN CAPITAL LETTER AE
      { "\u00E6", new Byte((byte) 0x1D) },   // LATIN SMALL LETTER AE
      { "\u00DF", new Byte((byte) 0x1E) },   // LATIN SMALL LETTER SHARP S (German)
      { "\u00C9", new Byte((byte) 0x1F) },   // LATIN CAPITAL LETTER E WITH ACUTE
      { "\u0020", new Byte((byte) 0x20) },   // SPACE
      { "\u0021", new Byte((byte) 0x21) },   // EXCLAMATION MARK
      { "\\u0022", new Byte((byte) 0x22) },   // QUOTATION MARK
      { "\u0023", new Byte((byte) 0x23) },   // NUMBER SIGN
      { "\u00A4", new Byte((byte) 0x24) },   // CURRENCY SIGN
      { "\u0025", new Byte((byte) 0x25) },   // PERCENT SIGN
      { "\u0026", new Byte((byte) 0x26) },   // AMPERSAND
      { "\u0027", new Byte((byte) 0x27) },   // APOSTROPHE
      { "\u0028", new Byte((byte) 0x28) },   // LEFT PARENTHESIS
      { "\u0029", new Byte((byte) 0x29) },   // RIGHT PARENTHESIS
      { "\u002A", new Byte((byte) 0x2A) },   // ASTERISK
      { "\u002B", new Byte((byte) 0x2B) },   // PLUS SIGN
      { "\u002C", new Byte((byte) 0x2C) },   // COMMA
      { "\u002D", new Byte((byte) 0x2D) },   // HYPHEN-MINUS
      { "\u002E", new Byte((byte) 0x2E) },   // FULL STOP
      { "\u002F", new Byte((byte) 0x2F) },   // SOLIDUS
      { "\u0030", new Byte((byte) 0x30) },   // DIGIT ZERO
      { "\u0031", new Byte((byte) 0x31) },   // DIGIT ONE
      { "\u0032", new Byte((byte) 0x32) },   // DIGIT TWO
      { "\u0033", new Byte((byte) 0x33) },   // DIGIT THREE
      { "\u0034", new Byte((byte) 0x34) },   // DIGIT FOUR
      { "\u0035", new Byte((byte) 0x35) },   // DIGIT FIVE
      { "\u0036", new Byte((byte) 0x36) },   // DIGIT SIX
      { "\u0037", new Byte((byte) 0x37) },   // DIGIT SEVEN
      { "\u0038", new Byte((byte) 0x38) },   // DIGIT EIGHT
      { "\u0039", new Byte((byte) 0x39) },   // DIGIT NINE
      { "\u003A", new Byte((byte) 0x3A) },   // COLON
      { "\u003B", new Byte((byte) 0x3B) },   // SEMICOLON
      { "\u003C", new Byte((byte) 0x3C) },   // LESS-THAN SIGN
      { "\u003D", new Byte((byte) 0x3D) },   // EQUALS SIGN
      { "\u003E", new Byte((byte) 0x3E) },   // GREATER-THAN SIGN
      { "\u003F", new Byte((byte) 0x3F) },   // QUESTION MARK
      { "\u00A1", new Byte((byte) 0x40) },   // INVERTED EXCLAMATION MARK
      { "\u0041", new Byte((byte) 0x41) },   // LATIN CAPITAL LETTER A
      { "\u0042", new Byte((byte) 0x42) },   // LATIN CAPITAL LETTER B
      { "\u0043", new Byte((byte) 0x43) },   // LATIN CAPITAL LETTER C
      { "\u0044", new Byte((byte) 0x44) },   // LATIN CAPITAL LETTER D
      { "\u0045", new Byte((byte) 0x45) },   // LATIN CAPITAL LETTER E
      { "\u0046", new Byte((byte) 0x46) },   // LATIN CAPITAL LETTER F
      { "\u0047", new Byte((byte) 0x47) },   // LATIN CAPITAL LETTER G
      { "\u0048", new Byte((byte) 0x48) },   // LATIN CAPITAL LETTER H
      { "\u0049", new Byte((byte) 0x49) },   // LATIN CAPITAL LETTER I
      { "\u004A", new Byte((byte) 0x4A) },   // LATIN CAPITAL LETTER J
      { "\u004B", new Byte((byte) 0x4B) },   // LATIN CAPITAL LETTER K
      { "\u004C", new Byte((byte) 0x4C) },   // LATIN CAPITAL LETTER L
      { "\u004D", new Byte((byte) 0x4D) },   // LATIN CAPITAL LETTER M
      { "\u004E", new Byte((byte) 0x4E) },   // LATIN CAPITAL LETTER N
      { "\u004F", new Byte((byte) 0x4F) },   // LATIN CAPITAL LETTER O
      { "\u0050", new Byte((byte) 0x50) },   // LATIN CAPITAL LETTER P
      { "\u0051", new Byte((byte) 0x51) },   // LATIN CAPITAL LETTER Q
      { "\u0052", new Byte((byte) 0x52) },   // LATIN CAPITAL LETTER R
      { "\u0053", new Byte((byte) 0x53) },   // LATIN CAPITAL LETTER S
      { "\u0054", new Byte((byte) 0x54) },   // LATIN CAPITAL LETTER T
      { "\u0055", new Byte((byte) 0x55) },   // LATIN CAPITAL LETTER U
      { "\u0056", new Byte((byte) 0x56) },   // LATIN CAPITAL LETTER V
      { "\u0057", new Byte((byte) 0x57) },   // LATIN CAPITAL LETTER W
      { "\u0058", new Byte((byte) 0x58) },   // LATIN CAPITAL LETTER X
      { "\u0059", new Byte((byte) 0x59) },   // LATIN CAPITAL LETTER Y
      { "\u005A", new Byte((byte) 0x5A) },   // LATIN CAPITAL LETTER Z
      { "\u00C4", new Byte((byte) 0x5B) },   // LATIN CAPITAL LETTER A WITH DIAERESIS
      { "\u00D6", new Byte((byte) 0x5C) },   // LATIN CAPITAL LETTER O WITH DIAERESIS
      { "\u00D1", new Byte((byte) 0x5D) },   // LATIN CAPITAL LETTER N WITH TILDE
      { "\u00DC", new Byte((byte) 0x5E) },   // LATIN CAPITAL LETTER U WITH DIAERESIS
      { "\u00A7", new Byte((byte) 0x5F) },   // SECTION SIGN
      { "\u00BF", new Byte((byte) 0x60) },   // INVERTED QUESTION MARK
      { "\u0061", new Byte((byte) 0x61) },   // LATIN SMALL LETTER A
      { "\u0062", new Byte((byte) 0x62) },   // LATIN SMALL LETTER B
      { "\u0063", new Byte((byte) 0x63) },   // LATIN SMALL LETTER C
      { "\u0064", new Byte((byte) 0x64) },   // LATIN SMALL LETTER D
      { "\u0065", new Byte((byte) 0x65) },   // LATIN SMALL LETTER E
      { "\u0066", new Byte((byte) 0x66) },   // LATIN SMALL LETTER F
      { "\u0067", new Byte((byte) 0x67) },   // LATIN SMALL LETTER G
      { "\u0068", new Byte((byte) 0x68) },   // LATIN SMALL LETTER H
      { "\u0069", new Byte((byte) 0x69) },   // LATIN SMALL LETTER I
      { "\u006A", new Byte((byte) 0x6A) },   // LATIN SMALL LETTER J
      { "\u006B", new Byte((byte) 0x6B) },   // LATIN SMALL LETTER K
      { "\u006C", new Byte((byte) 0x6C) },   // LATIN SMALL LETTER L
      { "\u006D", new Byte((byte) 0x6D) },   // LATIN SMALL LETTER M
      { "\u006E", new Byte((byte) 0x6E) },   // LATIN SMALL LETTER N
      { "\u006F", new Byte((byte) 0x6F) },   // LATIN SMALL LETTER O
      { "\u0070", new Byte((byte) 0x70) },   // LATIN SMALL LETTER P
      { "\u0071", new Byte((byte) 0x71) },   // LATIN SMALL LETTER Q
      { "\u0072", new Byte((byte) 0x72) },   // LATIN SMALL LETTER R
      { "\u0073", new Byte((byte) 0x73) },   // LATIN SMALL LETTER S
      { "\u0074", new Byte((byte) 0x74) },   // LATIN SMALL LETTER T
      { "\u0075", new Byte((byte) 0x75) },   // LATIN SMALL LETTER U
      { "\u0076", new Byte((byte) 0x76) },   // LATIN SMALL LETTER V
      { "\u0077", new Byte((byte) 0x77) },   // LATIN SMALL LETTER W
      { "\u0078", new Byte((byte) 0x78) },   // LATIN SMALL LETTER X
      { "\u0079", new Byte((byte) 0x79) },   // LATIN SMALL LETTER Y
      { "\u007A", new Byte((byte) 0x7A) },   // LATIN SMALL LETTER Z
      { "\u00E4", new Byte((byte) 0x7B) },   // LATIN SMALL LETTER A WITH DIAERESIS
      { "\u00F6", new Byte((byte) 0x7C) },   // LATIN SMALL LETTER O WITH DIAERESIS
      { "\u00F1", new Byte((byte) 0x7D) },   // LATIN SMALL LETTER N WITH TILDE
      { "\u00FC", new Byte((byte) 0x7E) },   // LATIN SMALL LETTER U WITH DIAERESIS
      { "\u00E0", new Byte((byte) 0x7F) }   // LATIN SMALL LETTER A WITH GRAVE
    */

}
