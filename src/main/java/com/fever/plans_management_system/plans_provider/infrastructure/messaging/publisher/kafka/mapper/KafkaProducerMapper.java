package com.fever.plans_management_system.plans_provider.infrastructure.messaging.publisher.kafka.mapper;

import com.fever.plans_management_system.kafka_messaging_contracts.dto.BasePlan;
import com.fever.plans_management_system.kafka_messaging_contracts.dto.Plan;
import com.fever.plans_management_system.kafka_messaging_contracts.dto.Zone;
import com.fever.plans_management_system.plans_provider.domain.event.CreateBasePlanEvent;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducerMapper {
    public BasePlan createBasePlanEventTobasePlanAvro(CreateBasePlanEvent createBasePlanEvent) {
        return new BasePlan(createBasePlanEvent.getBasePlan().getId().value(),
                createBasePlanEvent.getBasePlan().getTitle(),
                createBasePlanEvent.getBasePlan().getSellMode(),
                createBasePlanEvent.getBasePlan().getPlanList().stream().map(plan -> {return new Plan(
                        plan.getId().value(), plan.getStartDate().toString(),
                        plan.getEndDate().toString(), plan.getSellFrom().toString(), plan.getSellTo().toString(),
                        plan.isSoldOut(), plan.getZones().stream().map(zone -> {return new Zone(
                                zone.getId().value(),
                        zone.getCapacity(), String.valueOf(zone.getPrice()), zone.getName(), zone.isNumbered());
                }).toList());}).toList());
    }

}
