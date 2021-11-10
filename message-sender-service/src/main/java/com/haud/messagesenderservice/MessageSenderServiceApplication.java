package com.haud.messagesenderservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.haud.messagesenderservice.model.MessageContent;

@SpringBootApplication
@EnableEurekaClient
@EnableBinding(Processor.class)
public class MessageSenderServiceApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(MessageSenderServiceApplication.class);

	private ObjectMapper mapper = new ObjectMapper();

	public static void main(String[] args) {
		SpringApplication.run(MessageSenderServiceApplication.class, args);
	}
	@StreamListener(Processor.INPUT)
	public void receiveOrder(MessageContent msg) throws JsonProcessingException {
		LOGGER.info("Message received: {}", mapper.writeValueAsString(msg));
	}
}
