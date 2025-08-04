package com.fever.plans_management_system.plans_provider.infrastructure.messaging.publisher;

import com.fever.plans_management_system.plans_provider.application.port.out.BasePlanEventPublisher;
import com.fever.plans_management_system.plans_provider.domain.event.CreateBasePlanEvent;
import com.fever.plans_management_system.plans_provider.infrastructure.messaging.publisher.kafka.KafkaProducer;
import org.springframework.stereotype.Service;

@Service
public class BasePlanPublisherImpl implements BasePlanEventPublisher {

    private final KafkaProducer kafkaProducer;

    public BasePlanPublisherImpl(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @Override
    public void publish(CreateBasePlanEvent event) {
        kafkaProducer.send("base-plan-topic", event);
    }
}
