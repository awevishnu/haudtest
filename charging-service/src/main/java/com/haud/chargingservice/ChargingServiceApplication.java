package com.haud.chargingservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.haud.chargingservice.messaging.MessageSender;
import com.haud.chargingservice.model.MessageContent;

@SpringBootApplication
public class ChargingServiceApplication {
private static final Logger LOGGER = LoggerFactory.getLogger(ChargingServiceApplication.class);
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	MessageSender sender;
	
	public static void main(String[] args) {
		SpringApplication.run(ChargingServiceApplication.class, args);
	}
	@StreamListener(Processor.INPUT)
	public void receiveOrder(MessageContent msg) throws JsonProcessingException {
		LOGGER.info("Message received: {}", mapper.writeValueAsString(msg));
		
	}
	
}
