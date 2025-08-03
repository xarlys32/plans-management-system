package com.fever.plans_management_system.plans_management.infrastructure.messaging.listener.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "plans.events", groupId = "plans-event")
    public void listen() {

    }
}
