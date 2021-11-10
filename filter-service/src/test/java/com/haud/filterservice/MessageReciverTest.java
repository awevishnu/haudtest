package com.haud.filterservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.haud.filterservice.model.MessageContent;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MessageReciverTest {
private static final Logger LOGGER = LoggerFactory.getLogger(MessageReciverTest.class);
	
	@Autowired
	private Processor processor;
	@Autowired
	private MessageCollector messageCollector;
	
	@Test
	@SuppressWarnings("unchecked")
	public void testAccepted() {
		MessageContent msg = new MessageContent();
		
		processor.input().send(MessageBuilder.withPayload(msg).build());
		Message<MessageContent> received = (Message<MessageContent>) messageCollector.forChannel(processor.output()).poll();
		LOGGER.info("Message response received: {}", received.getPayload());
		assertNotNull(received.getPayload());
	    assertEquals("No", received.getPayload().getMsgSpamStatus());
	}
}
