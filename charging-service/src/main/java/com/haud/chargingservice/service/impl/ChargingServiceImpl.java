package com.haud.chargingservice.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.haud.chargingservice.messaging.MessageSender;
import com.haud.chargingservice.model.MessageContent;
import com.haud.chargingservice.service.ChargingService;

public class ChargingServiceImpl implements ChargingService{
	
private static final Logger LOGGER = LoggerFactory.getLogger(ChargingServiceImpl.class);
	
	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	MessageSender sender;
	
	@Override
	public void process(final MessageContent msg) throws JsonProcessingException {
		msg.setMsgChargingStatus("Yes");
		sender.send(msg);
		LOGGER.info("Message Charged Successfully: {}", mapper.writeValueAsString(msg));
	}

}
