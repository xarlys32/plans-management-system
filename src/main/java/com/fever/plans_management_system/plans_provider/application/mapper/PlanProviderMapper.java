package com.fever.plans_management_system.plans_provider.application.mapper;

import com.fever.plans_management_system.plans_provider.application.command.ProcessPlanEventCommand;
import com.fever.plans_management_system.plans_provider.domain.entity.Plan;
import org.springframework.stereotype.Component;

@Component
public class PlanProviderMapper {
    public Plan processPlanEventCommandToPlan(ProcessPlanEventCommand processPlanEventCommand) {
        return Plan.builder()
                .id(processPlanEventCommand.id())
                .name(processPlanEventCommand.name())
                .sellMode(processPlanEventCommand.sellMode())
                .endsAt(processPlanEventCommand.availableTo())
                .startsAt(processPlanEventCommand.availableFrom())
                .build();
    }
}
