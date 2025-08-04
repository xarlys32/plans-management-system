package com.fever.plans_management_system.plans_management.infrastructure.messaging.listener.kafka;

import com.fever.plans_management_system.kafka_messaging_contracts.dto.BasePlan;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "base-plan-topic", groupId = "base-plan-event")
    public void listener(BasePlan basePlan) {

    }
}
