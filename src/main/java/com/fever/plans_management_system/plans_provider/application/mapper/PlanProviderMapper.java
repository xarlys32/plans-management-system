package com.fever.plans_management_system.plans_provider.application.mapper;

import com.fever.plans_management_system.plans_provider.application.command.ProcessPlanEventCommand;
import com.fever.plans_management_system.plans_provider.application.record.PlanProviderRecord;
import com.fever.plans_management_system.plans_provider.application.record.ZoneProviderRecord;
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
                .id(new BasePlanProviderId(processPlanEventCommand.basePlanProviderRecord().id()))
                .title(processPlanEventCommand.basePlanProviderRecord().title())
                .sellMode(processPlanEventCommand.basePlanProviderRecord().sellMode())
                .organizerCompanyId(processPlanEventCommand.basePlanProviderRecord().organizerCompanyId())
                .planList(processPlanEventCommand.basePlanProviderRecord().plans().stream().map(this::planEventCommandToPlan).toList())
                .build();
    }

    private PlanProvider planEventCommandToPlan(PlanProviderRecord planEventCommand) {
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
    private ZoneProvider zoneRecordToZones(ZoneProviderRecord zoneProviderRecord) {
        return ZoneProvider.builder()
                .id(new ZoneProviderId(zoneProviderRecord.id()))
                .name(zoneProviderRecord.name())
                .capacity(zoneProviderRecord.capacity())
                .isNumbered(zoneProviderRecord.isNumbered())
                .price(zoneProviderRecord.price())
                .build();
    }
}
