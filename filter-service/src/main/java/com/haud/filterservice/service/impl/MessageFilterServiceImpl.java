package com.haud.filterservice.service.impl;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.haud.filterservice.messaging.MessageSender;
import com.haud.filterservice.model.MessageContent;
import com.haud.filterservice.service.MessageFilterService;
@Service
public class MessageFilterServiceImpl implements MessageFilterService{
private static final Logger LOGGER = LoggerFactory.getLogger(MessageFilterServiceImpl.class);
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	MessageSender sender;
	

	public boolean checkValidMessage(String strMessage) {
		List<String> blackListedWords=Arrays.asList("Act now","100% free","Cash bonus","Click here", 
				"Earn extra cash", "Free gift");
		   boolean match = blackListedWords.stream().anyMatch(strMessage::contains);
        return match;
	}

	public boolean checkIfDestinationAllowed(String destinationId) {
		List<String> allowedDestinations=Arrays.asList("+919539789319","+917411248435");
		boolean match = allowedDestinations.stream().anyMatch(destinationId::contains);
		 return match;
	}

	public void process(MessageContent msg) throws JsonProcessingException {
	
		if(checkValidMessage(msg.getMessageContent())==true && checkIfDestinationAllowed(msg.getDestination())==true){
			msg.setMsgSpamStatus("Yes");
		}
		else
		{
			msg.setMsgSpamStatus("No");
		}
		sender.send(msg);
		LOGGER.info("Message processed: {}", mapper.writeValueAsString(msg));
	}

}
