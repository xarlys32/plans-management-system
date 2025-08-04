package com.fever.plans_management_system.plans_management.application.port.out;


import com.fever.plans_management_system.plans_management.domain.entity.BasePlan;

public interface ManagementRepository {
    BasePlan save(BasePlan basePlan);
}
