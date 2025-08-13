package com.fever.plans_management_system.plans_management.infrastructure.repository.postgres.mapper;

import com.fever.plans_management_system.plans_management.domain.entity.BasePlan;
import com.fever.plans_management_system.plans_management.infrastructure.repository.postgres.entity.BasePlanEntity;
import com.fever.plans_management_system.plans_management.infrastructure.repository.postgres.entity.PlanEntity;
import com.fever.plans_management_system.plans_management.infrastructure.repository.postgres.entity.ZoneEntity;
import org.springframework.stereotype.Component;

@Component
public class ManagementRepoMapper {
    public BasePlanEntity basePlanToEntity(BasePlan basePlan) {
        // In progress
        return BasePlanEntity.builder()
                .basePlanId(basePlan.getId().value())
                .title(basePlan.getTitle())
                .sellMode(basePlan.getSellMode())
                .organizerCompanyId(basePlan.getOrganizerCompanyId())
                .plans(basePlan.getPlanList().stream()
                        .map(plan -> PlanEntity.builder()
                                .planId(plan.getId().value())
                                .sellFrom(plan.getSellFrom())
                                .sellTo(plan.getSellTo())
                                .soldOut(plan.isSoldOut())
                                .planStartDate(plan.getStartDate())
                                .planEndDate(plan.getEndDate())
                                .zones(plan.getZones().stream()
                                        .map(zone -> ZoneEntity.builder()
                                                .zoneId(zone.getId().value())
                                                .name(zone.getName())
                                                .capacity(zone.getCapacity())
                                                .price(zone.getPrice())
                                                .numbered(zone.isNumbered())
                                                .build()).toList())
                                .build()
                        ).toList())
                .build();
    }

    public BasePlan basePlanEntityToDomain(BasePlanEntity basePlanEntity) {
        return BasePlan.builder().build();
    }
}
