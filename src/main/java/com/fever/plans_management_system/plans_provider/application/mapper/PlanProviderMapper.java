package com.fever.plans_management_system.plans_provider.application.mapper;

import com.fever.plans_management_system.plans_provider.application.command.ProcessPlanEventCommand;
import com.fever.plans_management_system.plans_provider.application.record.PlanRecord;
import com.fever.plans_management_system.plans_provider.application.record.ZoneRecord;
import com.fever.plans_management_system.plans_provider.domain.entity.BasePlan;
import com.fever.plans_management_system.plans_provider.domain.entity.Plan;
import com.fever.plans_management_system.plans_provider.domain.entity.Zone;
import com.fever.plans_management_system.plans_provider.domain.valueobject.BasePlanId;
import com.fever.plans_management_system.plans_provider.domain.valueobject.PlanId;
import com.fever.plans_management_system.plans_provider.domain.valueobject.ZoneId;
import org.springframework.stereotype.Component;

@Component
public class PlanProviderMapper {
    public BasePlan processBasePlanEventCommandToPlan(ProcessPlanEventCommand processPlanEventCommand) {
        return BasePlan.builder()
                .id(new BasePlanId(processPlanEventCommand.basePlanRecord().id()))
                .title(processPlanEventCommand.basePlanRecord().title())
                .sellMode(processPlanEventCommand.basePlanRecord().sellMode())
                .organizerCompanyId(processPlanEventCommand.basePlanRecord().organizerCompanyId())
                .planList(processPlanEventCommand.basePlanRecord().plans().stream().map(this::planEventCommandToPlan).toList())
                .build();
    }

    private Plan planEventCommandToPlan(PlanRecord planEventCommand) {
        return Plan.builder()
                .id(new PlanId(planEventCommand.id()))
                .sellFrom(planEventCommand.sellFrom())
                .sellTo(planEventCommand.sellTo())
                .soldOut(planEventCommand.soldOut())
                .startDate(planEventCommand.startDate())
                .endDate(planEventCommand.endDate())
                .zones(planEventCommand.zones().stream().map(this::zoneRecordToZones).toList())
                .build();

    }
    private Zone zoneRecordToZones(ZoneRecord zoneRecord) {
        return Zone.builder()
                .id(new ZoneId(zoneRecord.id()))
                .name(zoneRecord.name())
                .capacity(zoneRecord.capacity())
                .isNumbered(zoneRecord.isNumbered())
                .price(zoneRecord.price())
                .build();
    }
}
