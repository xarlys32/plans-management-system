package com.fever.plans_management_system.plans_provider.application.mapper;

import com.fever.plans_management_system.plans_provider.application.command.ProcessPlanEventCommand;
import com.fever.plans_management_system.plans_provider.application.record.PlanRecord;
import com.fever.plans_management_system.plans_provider.application.record.ZoneRecord;
import com.fever.plans_management_system.plans_provider.domain.entity.BasePlanProvider;
import com.fever.plans_management_system.plans_provider.domain.entity.PlanProvider;
import com.fever.plans_management_system.plans_provider.domain.entity.ZoneProvider;
import com.fever.plans_management_system.plans_provider.domain.valueobject.BasePlanProviderId;
import com.fever.plans_management_system.plans_provider.domain.valueobject.PlanProviderId;
import com.fever.plans_management_system.plans_provider.domain.valueobject.ZoneProviderId;
import org.springframework.stereotype.Component;

@Component
public class PlanProviderMapper {
    public BasePlanProvider processBasePlanEventCommandToPlan(ProcessPlanEventCommand processPlanEventCommand) {
        return BasePlanProvider.builder()
                .id(new BasePlanProviderId(processPlanEventCommand.basePlanRecord().id()))
                .title(processPlanEventCommand.basePlanRecord().title())
                .sellMode(processPlanEventCommand.basePlanRecord().sellMode())
                .organizerCompanyId(processPlanEventCommand.basePlanRecord().organizerCompanyId())
                .planList(processPlanEventCommand.basePlanRecord().plans().stream().map(this::planEventCommandToPlan).toList())
                .build();
    }

    private PlanProvider planEventCommandToPlan(PlanRecord planEventCommand) {
        return PlanProvider.builder()
                .id(new PlanProviderId(planEventCommand.id()))
                .sellFrom(planEventCommand.sellFrom())
                .sellTo(planEventCommand.sellTo())
                .soldOut(planEventCommand.soldOut())
                .startDate(planEventCommand.startDate())
                .endDate(planEventCommand.endDate())
                .zones(planEventCommand.zones().stream().map(this::zoneRecordToZones).toList())
                .build();

    }
    private ZoneProvider zoneRecordToZones(ZoneRecord zoneRecord) {
        return ZoneProvider.builder()
                .id(new ZoneProviderId(zoneRecord.id()))
                .name(zoneRecord.name())
                .capacity(zoneRecord.capacity())
                .isNumbered(zoneRecord.isNumbered())
                .price(zoneRecord.price())
                .build();
    }
}
