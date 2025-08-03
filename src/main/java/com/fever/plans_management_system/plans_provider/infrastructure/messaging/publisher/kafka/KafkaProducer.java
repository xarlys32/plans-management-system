package com.fever.plans_management_system.plans_provider.infrastructure.messaging.publisher.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {
    private final KafkaTemplate<String, PlanEvent> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, PlanEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String topic, PlanEvent message) {
        kafkaTemplate.send(topic, message);
    }
}
