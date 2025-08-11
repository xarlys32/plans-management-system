package com.fever.plans_management_system.plans_provider.infrastructure.messaging.publisher.kafka;

import com.fever.plans_management_system.kafka_messaging_contracts.dto.BasePlanAvro;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {
    private final KafkaTemplate<String, BasePlanAvro> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, BasePlanAvro> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String topic, BasePlanAvro message) {
        kafkaTemplate.send(topic, message);
    }
}
