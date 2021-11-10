package com.haud.messagesenderservice.controller;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.haud.messagesenderservice.messaging.MessageSender;
import com.haud.messagesenderservice.model.MessageContent;

@RestController
public class MessageSenderController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MessageSenderController.class);
	private ObjectMapper mapper = new ObjectMapper();
	@Autowired
	MessageSender sender;

	 @PostMapping("/sendMessage")
	public MessageContent process(@RequestBody MessageContent msgContent) throws JsonProcessingException {
		boolean isSent = sender.send(msgContent);
		LOGGER.info("Message sent: {}", mapper.writeValueAsString(Collections.singletonMap("isSent", isSent)));
		return msgContent;
	}

}
