package com.fever.plans_management_system.plans_management.infrastructure.repository.postgres.mapper;

import com.fever.plans_management_system.plans_management.domain.entity.BasePlan;
import com.fever.plans_management_system.plans_management.domain.entity.Zone;
import com.fever.plans_management_system.plans_management.infrastructure.repository.postgres.entity.BasePlanEntity;
import com.fever.plans_management_system.plans_management.infrastructure.repository.postgres.entity.PlanEntity;
import com.fever.plans_management_system.plans_management.infrastructure.repository.postgres.entity.ZoneEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class ManagementRepoMapper {
    public BasePlanEntity basePlanToEntity(BasePlan basePlan, List<ZoneEntity> zones) {
        BasePlanEntity basePlanEntity = BasePlanEntity.builder()
                .basePlanId(basePlan.getId().value())
                .title(basePlan.getTitle())
                .sellMode(basePlan.getSellMode())
                .organizerCompanyId(basePlan.getOrganizerCompanyId())
                .build();
        List<PlanEntity> listPlans = basePlan.getPlanList().stream()
                .map(plan -> {
                        PlanEntity planEntity = PlanEntity.builder()
                        .planId(plan.getId().value())
                        .sellFrom(plan.getSellFrom())
                        .sellTo(plan.getSellTo())
                        .soldOut(plan.isSoldOut())
                        .planStartDate(plan.getStartDate())
                        .planEndDate(plan.getEndDate())
                        .basePlan(basePlanEntity).build();

                        List<ZoneEntity> listZones = plan.getZones().stream()
                                .map(zone -> {
                                    Optional<ZoneEntity> zoneSaved = zones.stream().filter(zoneInDb -> zoneInDb.getZoneId().equals(zone.getId().value())).findFirst();
                                    if (zoneSaved.isEmpty()) {
                                        ZoneEntity newZone = ZoneEntity.builder()
                                                .zoneId(zone.getId().value())
                                                .name(zone.getName())
                                                .capacity(zone.getCapacity())
                                                .price(zone.getPrice())
                                                .numbered(zone.isNumbered())
                                                .plan(planEntity)
                                                .build();
                                        zones.add(newZone);
                                        return newZone;
                                    } else {
                                        return zoneSaved.get();
                                    }
                                }).toList();
                        planEntity.setZones(listZones);
                        return planEntity;
                }
                ).toList();
        basePlanEntity.setPlans(listPlans);

        return basePlanEntity;
    }

    public BasePlan basePlanEntityToDomain(BasePlanEntity basePlanEntity) {
        return BasePlan.builder().build();
    }
}
