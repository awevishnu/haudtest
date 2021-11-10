package com.haud.messagesenderservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class MessageContent {
	
	private int msgId;
	
	private String sender;
	
	private String messageContent;
	
	private String destination;
}
