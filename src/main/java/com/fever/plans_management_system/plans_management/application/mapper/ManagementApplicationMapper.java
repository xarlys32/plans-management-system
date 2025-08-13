package com.fever.plans_management_system.plans_management.application.mapper;

import com.fever.plans_management_system.plans_management.application.command.ProcessMessageCommand;
import com.fever.plans_management_system.plans_management.application.record.BasePlanRecord;
import com.fever.plans_management_system.plans_management.application.record.PlanRecord;
import com.fever.plans_management_system.plans_management.application.record.ZoneRecord;
import com.fever.plans_management_system.plans_management.domain.entity.BasePlan;
import com.fever.plans_management_system.plans_management.domain.entity.Plan;
import com.fever.plans_management_system.plans_management.domain.entity.Zone;
import com.fever.plans_management_system.plans_management.domain.valueobject.BasePlanId;
import com.fever.plans_management_system.plans_management.domain.valueobject.PlanId;
import com.fever.plans_management_system.plans_management.domain.valueobject.ZoneId;
import com.fever.plans_management_system.plans_management.infrastructure.repository.postgres.entity.BasePlanEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManagementApplicationMapper {
    public BasePlan processMessageCommandToBasePlan(ProcessMessageCommand processMessageCommand) {
        return BasePlan.builder()
                .id(new BasePlanId(processMessageCommand.basePlanRecord().id()))
                .organizerCompanyId(processMessageCommand.basePlanRecord().organizerCompanyId())
                .title(processMessageCommand.basePlanRecord().title())
                .sellMode(processMessageCommand.basePlanRecord().sellMode())
                .planList(processMessageCommand.basePlanRecord().plans().stream().map(planRecord ->  Plan
                        .builder()
                        .id(new PlanId(planRecord.id()))
                        .startDate(planRecord.startDate())
                        .endDate(planRecord.endDate())
                        .sellFrom(planRecord.sellFrom())
                        .sellTo(planRecord.sellTo())
                        .soldOut(planRecord.soldOut())
                        .zones(planRecord.zones().stream().map(zoneRecord -> Zone
                                .builder()
                                .id(new ZoneId(zoneRecord.id()))
                                .name(zoneRecord.name())
                                .capacity(zoneRecord.capacity())
                                .price(zoneRecord.price())
                                .isNumbered(zoneRecord.isNumbered())
                                .build()).toList())
                        .build()
                ).toList())
                .build();
    }

    public List<BasePlanRecord> listOfBasePlanEntitiesToRecords(List<BasePlanEntity> eventsFromDates) {
        return eventsFromDates.stream().map(basePlanEntity -> new BasePlanRecord(
                basePlanEntity.getBasePlanId(),
                basePlanEntity.getTitle(),
                basePlanEntity.getSellMode(),
                basePlanEntity.getOrganizerCompanyId(),
                basePlanEntity.getPlans().stream().map(planEntity -> new PlanRecord(
                        planEntity.getPlanId(),
                        planEntity.getPlanStartDate(),
                        planEntity.getPlanEndDate(),
                        planEntity.getSellFrom(),
                        planEntity.getSellTo(),
                        planEntity.getSoldOut(),
                        planEntity.getZones().stream().map(zoneEntity -> new ZoneRecord(
                                zoneEntity.getZoneId(),
                                zoneEntity.getName(),
                                zoneEntity.getCapacity(),
                                zoneEntity.getPrice(),
                                zoneEntity.getNumbered()
                        )).toList()
                )).toList()
        )).toList();
    }
}
