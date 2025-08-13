package com.fever.plans_management_system.plans_management.application.port.out;


import com.fever.plans_management_system.plans_management.domain.entity.BasePlan;
import com.fever.plans_management_system.plans_management.infrastructure.repository.postgres.entity.BasePlanEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface ManagementRepository {
    BasePlan save(BasePlan basePlan);
    List<BasePlanEntity> findEventsFromDates(LocalDateTime from, LocalDateTime to);
}
