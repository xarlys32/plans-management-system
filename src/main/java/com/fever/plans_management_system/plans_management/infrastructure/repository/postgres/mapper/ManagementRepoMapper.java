package com.fever.plans_management_system.plans_management.infrastructure.repository.postgres.mapper;

import com.fever.plans_management_system.plans_management.domain.entity.BasePlan;
import com.fever.plans_management_system.plans_management.infrastructure.repository.postgres.entity.BasePlanEntity;
import org.springframework.stereotype.Component;

@Component
public class ManagementRepoMapper {
    public BasePlanEntity basePlanToEntity(BasePlan basePlan) {
        // In progress
        return BasePlanEntity.builder()
                .basePlanId(basePlan.getId().value())
                .title(basePlan.getTitle())
                .build();
    }

    public BasePlan basePlanEntityToDomain(BasePlanEntity basePlanEntity) {
        return BasePlan.builder().build();
    }
}
