package com.fever.plans_management_system.plans_provider.application.handler;

import com.fever.plans_management_system.plans_provider.application.command.ProcessPlanEventCommand;
import com.fever.plans_management_system.plans_provider.application.mapper.PlanProviderMapper;
import com.fever.plans_management_system.plans_provider.domain.entity.Plan;
import org.springframework.stereotype.Service;

@Service
public class ProcessPlanEventCommandHandler {

    private final PlanProviderMapper planProviderMapper;

    public ProcessPlanEventCommandHandler(PlanProviderMapper planProviderMapper) {
        this.planProviderMapper = planProviderMapper;
    }

    public void processAndPublishPlan(ProcessPlanEventCommand processPlanEventCommand) {
        // Llamar y validar al dominio
        //Publicar evento validado
        Plan plan = planProviderMapper.processPlanEventCommandToPlan(processPlanEventCommand);
    }
}
