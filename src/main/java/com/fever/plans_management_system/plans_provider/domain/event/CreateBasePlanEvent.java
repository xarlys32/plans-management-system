package com.fever.plans_management_system.plans_provider.domain.event;

import com.fever.plans_management_system.plans_provider.domain.entity.BasePlan;
import com.fever.plans_management_system.shared.domain.event.DomainEvent;

public class CreateBasePlanEvent extends DomainEvent {

    private final BasePlan basePlan;

    public CreateBasePlanEvent(String eventType, BasePlan basePlan) {
        super(eventType);
        this.basePlan = basePlan;
    }

    public BasePlan getBasePlan() {
        return basePlan;
    }

}
