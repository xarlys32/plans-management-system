package com.fever.plans_management_system.plans_management.infrastructure.messaging.listener.kafka;

import com.fever.plans_management_system.kafka_messaging_contracts.dto.BasePlanAvro;
import com.fever.plans_management_system.plans_management.application.command.ProcessMessageCommand;
import com.fever.plans_management_system.plans_management.application.handler.ProcessMessageCommandHandler;
import com.fever.plans_management_system.plans_management.infrastructure.messaging.listener.kafka.config.KafkaConsumer;
import com.fever.plans_management_system.plans_management.infrastructure.messaging.listener.kafka.mapper.KafkaConsumerMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BasePlanKafkaMessageConsumer implements KafkaConsumer<BasePlanAvro> {

    private final ProcessMessageCommandHandler processMessageCommandHandler;
    private final KafkaConsumerMapper kafkaConsumerMapper;

    public BasePlanKafkaMessageConsumer(ProcessMessageCommandHandler processMessageCommandHandler, KafkaConsumerMapper kafkaConsumerMapper) {
        this.processMessageCommandHandler = processMessageCommandHandler;
        this.kafkaConsumerMapper = kafkaConsumerMapper;
    }


    @KafkaListener(topics = "base-plan-topic", groupId = "base-plan-event")
    public void receiveSingle(BasePlanAvro message, Acknowledgment ack) {
        log.info("Receive single event "+ message);
        processMessageCommandHandler.processMessageAndPersist(new ProcessMessageCommand(
                kafkaConsumerMapper.basePlanAvroToRecordCommand(message)));
        ack.acknowledge();
    }
}
