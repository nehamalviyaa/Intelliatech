package com.info.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {
	
	@Value("${kafka.topic.order}")
	private String orderTopic;
	
	
	@KafkaListener(
		topics = "${kafka.topic.order}" ,
		groupId = "${spring.kafka.consumer.group-id}"
			
			)
	public void consumeOrder(String message , Acknowledgment ack) {
		try {
			
			 processOrder(message);
			 ack.acknowledge();
			
		}catch(Exception e) {
			 System.out.println("Failed to process message: " + e.getMessage());
	           
			
		}
		
		
	}
	
	public void processOrder(String message) {
		
		System.out.println("Notification received for Order : "+message);
	}

}
