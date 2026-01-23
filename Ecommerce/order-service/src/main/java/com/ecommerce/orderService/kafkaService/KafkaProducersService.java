package com.ecommerce.orderService.kafkaService;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducersService {
	private static final String TOPIC = "order-placed";
	
	private final KafkaTemplate<String, String> kafkaTemplate;
	
	public KafkaProducersService(KafkaTemplate<String, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}
	
	public void sendMessage(String message) {
		kafkaTemplate.send(TOPIC,message);
		System.out.println("Send message to kafka : " + message);
	}
}
