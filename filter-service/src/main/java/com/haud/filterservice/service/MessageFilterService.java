package com.haud.filterservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.haud.filterservice.model.MessageContent;

public interface MessageFilterService {
	public boolean checkValidMessage(String strMessage);

	public boolean checkIfDestinationAllowed(String destinationId);
	
	public void process(MessageContent msg) throws JsonProcessingException ;
	
}
