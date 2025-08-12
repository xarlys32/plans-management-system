package com.fever.plans_management_system.plans_management.infrastructure.messaging.listener.kafka.mapper;

import com.fever.plans_management_system.kafka_messaging_contracts.dto.BasePlanAvro;
import com.fever.plans_management_system.plans_management.application.record.BasePlanRecord;
import com.fever.plans_management_system.plans_management.application.record.PlanRecord;
import com.fever.plans_management_system.plans_management.application.record.ZoneRecord;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class KafkaConsumerMapper {
    public BasePlanRecord basePlanAvroToRecordCommand(BasePlanAvro basePlanAvro) {
        return new BasePlanRecord(
                basePlanAvro.getBasePlanId(),
                basePlanAvro.getTitle().toString(),
                basePlanAvro.getSellMode().toString(),
                basePlanAvro.getOrganizerCompanyId(),
                basePlanAvro.getPlans().stream().map(planAvro -> new PlanRecord(planAvro.getPlanId(),
                        LocalDateTime.parse(planAvro.getPlanStartDate()),
                        LocalDateTime.parse(planAvro.getPlanEndDate()),
                        LocalDateTime.parse(planAvro.getSellFrom()),
                        LocalDateTime.parse(planAvro.getSellTo()),
                        planAvro.getSoldOut(),
                        planAvro.getZones().stream().map(zoneAvro -> new ZoneRecord(zoneAvro.getZoneId(),
                                zoneAvro.getName().toString(),
                                zoneAvro.getCapacity(),
                                new BigDecimal(zoneAvro.getPrice().toString()),
                                zoneAvro.getNumbered())).toList())).toList()
        );
    }
}
