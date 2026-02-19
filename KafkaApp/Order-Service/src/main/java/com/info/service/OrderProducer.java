package com.info.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.topic.order}")
    private String orderTopic;

    public OrderProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrder(String message) {

        CompletableFuture<SendResult<String, String>> future =
                kafkaTemplate.send(orderTopic, message);

        future.whenComplete((result, ex) -> {

            if (ex == null) {
                System.out.println("Message sent successfully");
                System.out.println("Topic      : " + result.getRecordMetadata().topic());
                System.out.println("Partition  : " + result.getRecordMetadata().partition());
                System.out.println("Offset     : " + result.getRecordMetadata().offset());
            } else {
                System.out.println("Failed to send message to Kafka");
                ex.printStackTrace();
            }
        });
    }
}
