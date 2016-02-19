package com.symulakr.dinstar.smsserver.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.symulakr.dinstar.smsserver.message.body.ReceiveSms;
import com.symulakr.dinstar.smsserver.message.enums.Encoding;
import com.symulakr.dinstar.smsserver.message.enums.SmsResult;
import com.symulakr.dinstar.smsserver.message.enums.SmsStatus;

@Entity
@Table(name = "sms")
public class SmsMessage
{

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private long id;
   @NotNull
   private Date date;
   private String clid;
   @NotNull
   private String src;
   @NotNull
   private String dst;
   private Encoding encoding;
   private String content;
   private short slice;
   private SmsStatus status;
   private SmsResult result;

   public SmsMessage()
   {
   }

   public SmsMessage(ReceiveSms receiveSms)
   {
      this.date = new Date();
      this.clid = "dinstar";
      this.src = receiveSms.getNumber();
      this.dst = Byte.toString(receiveSms.getPort());
      this.encoding = receiveSms.getEncoding();
      this.content = receiveSms.getContent();
      this.status = SmsStatus.NEW;
      this.result = SmsResult.SUCCEED;
   }

   public long getId()
   {
      return id;
   }

   public void setId(long id)
   {
      this.id = id;
   }

   public Date getDate()
   {
      return date;
   }

   public void setDate(Date date)
   {
      this.date = date;
   }

   public String getClid()
   {
      return clid;
   }

   public void setClid(String clid)
   {
      this.clid = clid;
   }

   public String getSrc()
   {
      return src;
   }

   public void setSrc(String src)
   {
      this.src = src;
   }

   public String getDst()
   {
      return dst;
   }

   public void setDst(String dst)
   {
      this.dst = dst;
   }

   public Encoding getEncoding()
   {
      return encoding;
   }

   public void setEncoding(Encoding encoding)
   {
      this.encoding = encoding;
   }

   public String getContent()
   {
      return content;
   }

   public void setContent(String content)
   {
      this.content = content;
   }

   public short getSlice()
   {
      return slice;
   }

   public void setSlice(short slice)
   {
      this.slice = slice;
   }

   public SmsStatus getStatus()
   {
      return status;
   }

   public void setStatus(SmsStatus status)
   {
      this.status = status;
   }

   public SmsResult getResult()
   {
      return result;
   }

   public void setResult(SmsResult result)
   {
      this.result = result;
   }
}
