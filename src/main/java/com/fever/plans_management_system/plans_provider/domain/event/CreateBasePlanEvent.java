package com.fever.plans_management_system.plans_provider.domain.event;

import com.fever.plans_management_system.plans_provider.domain.entity.BasePlanProvider;
import com.fever.plans_management_system.shared.domain.event.DomainEvent;

public class CreateBasePlanEvent extends DomainEvent {

    private final BasePlanProvider basePlanProvider;

    public CreateBasePlanEvent(String eventType, BasePlanProvider basePlanProvider) {
        super(eventType);
        this.basePlanProvider = basePlanProvider;
    }

    public BasePlanProvider getBasePlanProvider() {
        return basePlanProvider;
    }

}
