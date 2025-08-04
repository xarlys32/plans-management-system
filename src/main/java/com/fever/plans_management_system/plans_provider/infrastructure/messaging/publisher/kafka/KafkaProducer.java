package com.fever.plans_management_system.plans_provider.infrastructure.messaging.publisher.kafka;

import com.fever.plans_management_system.plans_provider.domain.event.CreateBasePlanEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {
    private final KafkaTemplate<String, CreateBasePlanEvent> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, CreateBasePlanEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String topic, CreateBasePlanEvent message) {
        kafkaTemplate.send(topic, message);
    }
}
