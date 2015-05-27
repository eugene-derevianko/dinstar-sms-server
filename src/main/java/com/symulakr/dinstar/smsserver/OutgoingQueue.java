package com.symulakr.dinstar.smsserver;

import java.util.concurrent.ConcurrentLinkedDeque;

import org.springframework.stereotype.Component;

import com.symulakr.dinstar.smsserver.message.Message;

@Component
public class OutgoingQueue extends ConcurrentLinkedDeque<Message>
{
}
