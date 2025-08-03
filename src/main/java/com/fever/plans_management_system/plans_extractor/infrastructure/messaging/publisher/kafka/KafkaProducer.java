package com.fever.plans_management_system.plans_extractor.infrastructure.messaging.publisher.kafka;

import com.fever.plans_management_system.plans_extractor.infrastructure.messaging.publisher.kafka.dto.PlanEventPublishDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {
    private final KafkaTemplate<String, PlanEventPublishDTO> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, PlanEventPublishDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String topic, PlanEventPublishDTO message) {
        kafkaTemplate.send(topic, message);
    }
}
