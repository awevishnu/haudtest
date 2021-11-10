package com.haud.chargingservice.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.haud.chargingservice.model.MessageContent;

@Service
public class MessageSender {
	
	@Autowired
	private Source source;

	public boolean send(MessageContent msg) {
		return this.source.output().send(MessageBuilder.withPayload(msg).build());
	}
}
