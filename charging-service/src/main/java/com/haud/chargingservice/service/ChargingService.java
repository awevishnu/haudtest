package com.haud.chargingservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.haud.chargingservice.model.MessageContent;

public interface ChargingService {

	public void process(MessageContent msg) throws JsonProcessingException;
}
