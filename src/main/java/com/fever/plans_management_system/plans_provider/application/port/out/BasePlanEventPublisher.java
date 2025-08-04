package com.fever.plans_management_system.plans_provider.application.port.out;

import com.fever.plans_management_system.plans_provider.domain.event.CreateBasePlanEvent;

public interface BasePlanEventPublisher {
     void publish(CreateBasePlanEvent event);
}
