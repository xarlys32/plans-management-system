package com.fever.plans_management_system.plans_provider.infrastructure.messaging.publisher.kafka.mapper;

import com.fever.plans_management_system.kafka_messaging_contracts.dto.BasePlanAvro;
import com.fever.plans_management_system.kafka_messaging_contracts.dto.PlanAvro;
import com.fever.plans_management_system.kafka_messaging_contracts.dto.ZoneAvro;
import com.fever.plans_management_system.plans_provider.domain.event.CreateBasePlanEvent;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducerMapper {
    public BasePlanAvro createBasePlanEventTobasePlanAvro(CreateBasePlanEvent createBasePlanEvent) {
        return new BasePlanAvro(createBasePlanEvent.getBasePlanProvider().getId().value(),
                createBasePlanEvent.getBasePlanProvider().getTitle(),
                createBasePlanEvent.getBasePlanProvider().getSellMode(),
                createBasePlanEvent.getBasePlanProvider().getPlanList().stream().map(plan -> {return new PlanAvro(
                        plan.getId().value(), plan.getStartDate().toString(),
                        plan.getEndDate().toString(), plan.getSellFrom().toString(), plan.getSellTo().toString(),
                        plan.isSoldOut(), plan.getZones().stream().map(zone -> {return new ZoneAvro(
                                zone.getId().value(),
                        zone.getCapacity(), String.valueOf(zone.getPrice()), zone.getName(), zone.isNumbered());
                }).toList());}).toList());
    }

}
