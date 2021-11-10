package com.haud.filterservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.haud.filterservice.model.MessageContent;
import com.haud.filterservice.service.MessageFilterService;

@SpringBootApplication
@EnableBinding(Processor.class)
@EnableEurekaClient
public class FilterServiceApplication {
private static final Logger LOGGER = LoggerFactory.getLogger(FilterServiceApplication.class);
	
	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	MessageFilterService service;
	
	public static void main(String[] args) {
		SpringApplication.run(FilterServiceApplication.class, args);
	}
	@StreamListener(Processor.INPUT)
	public void receiveOrder(MessageContent msg) throws JsonProcessingException {
		LOGGER.info("Message received: {}", mapper.writeValueAsString(msg));
		service.process(msg);
	}

}
