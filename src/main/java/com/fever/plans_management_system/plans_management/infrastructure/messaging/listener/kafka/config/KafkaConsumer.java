package com.fever.plans_management_system.plans_management.infrastructure.messaging.listener.kafka.config;

import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.kafka.support.Acknowledgment;

public interface KafkaConsumer<T extends SpecificRecordBase> {
    void receiveSingle(T message, Acknowledgment ack);
}
