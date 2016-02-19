package com.symulakr.dinstar.smsserver.models;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

@Transactional
public interface SmsMessageDao extends CrudRepository<SmsMessage, Long>
{



}
