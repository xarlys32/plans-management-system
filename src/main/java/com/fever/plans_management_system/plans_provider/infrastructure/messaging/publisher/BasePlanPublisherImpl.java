package com.fever.plans_management_system.plans_provider.infrastructure.messaging.publisher;

import com.fever.plans_management_system.plans_provider.application.port.out.BasePlanEventPublisher;
import com.fever.plans_management_system.plans_provider.domain.event.CreateBasePlanEvent;
import com.fever.plans_management_system.plans_provider.infrastructure.messaging.publisher.kafka.KafkaProducer;
import com.fever.plans_management_system.plans_provider.infrastructure.messaging.publisher.kafka.mapper.KafkaProducerMapper;
import org.springframework.stereotype.Service;

@Service
public class BasePlanPublisherImpl implements BasePlanEventPublisher {

    private final KafkaProducer kafkaProducer;
    private final KafkaProducerMapper kafkaProducerMapper;
    private final static String TOPIC = "base-plan-topic";

    public BasePlanPublisherImpl(KafkaProducer kafkaProducer, KafkaProducerMapper kafkaProducerMapper) {
        this.kafkaProducer = kafkaProducer;
        this.kafkaProducerMapper = kafkaProducerMapper;
    }

    @Override
    public void publish(CreateBasePlanEvent event) {
        kafkaProducer.send(TOPIC, kafkaProducerMapper.createBasePlanEventTobasePlanAvro(event));
    }
}
