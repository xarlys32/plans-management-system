package com.fever.plans_management_system.plans_provider.infrastructure.messaging.publisher.kafka.mapper;

import com.fever.plans_management_system.kafka_messaging_contracts.dto.BasePlanAvro;
import com.fever.plans_management_system.kafka_messaging_contracts.dto.PlanAvro;
import com.fever.plans_management_system.kafka_messaging_contracts.dto.ZoneAvro;
import com.fever.plans_management_system.plans_provider.domain.event.CreateBasePlanEvent;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducerMapper {
    public BasePlanAvro createBasePlanEventTobasePlanAvro(CreateBasePlanEvent createBasePlanEvent) {
        return BasePlanAvro.newBuilder()
                .setBasePlanId(createBasePlanEvent.getBasePlanProvider().getId().value())
                .setTitle(createBasePlanEvent.getBasePlanProvider().getTitle())
                .setOrganizerCompanyId(createBasePlanEvent.getBasePlanProvider().getOrganizerCompanyId())
                .setSellMode(createBasePlanEvent.getBasePlanProvider().getSellMode())
                .setPlans(createBasePlanEvent.getBasePlanProvider().getPlanList().stream().map(planProvider ->
                        PlanAvro.newBuilder()
                                .setPlanId(planProvider.getId().value())
                                .setPlanStartDate(planProvider.getStartDate().toString())
                                .setPlanEndDate(planProvider.getEndDate().toString())
                                .setSellFrom(planProvider.getSellFrom().toString())
                                .setSellTo(planProvider.getSellTo().toString())
                                .setSoldOut(planProvider.isSoldOut())
                                .setZones(planProvider.getZones().stream().map(zoneProvider ->
                                        ZoneAvro.newBuilder()
                                                .setZoneId(zoneProvider.getId().value())
                                                .setName(zoneProvider.getName())
                                                .setCapacity(zoneProvider.getCapacity())
                                                .setNumbered(zoneProvider.isNumbered())
                                                .setPrice(String.valueOf(zoneProvider.getPrice()))
                                                .build()).toList())
                                .build()).toList())
                .build();

    }

}
