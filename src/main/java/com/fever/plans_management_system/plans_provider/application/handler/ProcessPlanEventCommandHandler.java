package com.fever.plans_management_system.plans_provider.application.handler;

import com.fever.plans_management_system.plans_provider.application.command.ProcessPlanEventCommand;
import com.fever.plans_management_system.plans_provider.application.mapper.PlanProviderMapper;
import com.fever.plans_management_system.plans_provider.application.port.out.BasePlanEventPublisher;
import com.fever.plans_management_system.plans_provider.domain.entity.BasePlan;
import com.fever.plans_management_system.plans_provider.domain.event.CreateBasePlanEvent;
import org.springframework.stereotype.Service;

@Service
public class ProcessPlanEventCommandHandler {

    private final PlanProviderMapper planProviderMapper;
    private final BasePlanEventPublisher basePlanEventPublisher;

    public ProcessPlanEventCommandHandler(PlanProviderMapper planProviderMapper, BasePlanEventPublisher basePlanEventPublisher) {
        this.planProviderMapper = planProviderMapper;
        this.basePlanEventPublisher = basePlanEventPublisher;
    }

    public void processAndPublishPlan(ProcessPlanEventCommand processPlanEventCommand) {
        BasePlan basePlan = planProviderMapper.processBasePlanEventCommandToPlan(processPlanEventCommand);
        CreateBasePlanEvent event = basePlan.validateAndCreateEvent();
        basePlanEventPublisher.publish(event);
    }
}
