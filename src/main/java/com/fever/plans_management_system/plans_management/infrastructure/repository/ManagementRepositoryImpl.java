package com.fever.plans_management_system.plans_management.infrastructure.repository;

import com.fever.plans_management_system.plans_management.application.port.out.ManagementRepository;
import com.fever.plans_management_system.plans_management.domain.entity.BasePlan;
import com.fever.plans_management_system.plans_management.domain.entity.Plan;
import com.fever.plans_management_system.plans_management.domain.entity.Zone;
import com.fever.plans_management_system.plans_management.infrastructure.repository.postgres.BasePlanPostgresRepository;
import com.fever.plans_management_system.plans_management.infrastructure.repository.postgres.ZoneRepository;
import com.fever.plans_management_system.plans_management.infrastructure.repository.postgres.entity.BasePlanEntity;
import com.fever.plans_management_system.plans_management.infrastructure.repository.postgres.entity.ZoneEntity;
import com.fever.plans_management_system.plans_management.infrastructure.repository.postgres.mapper.ManagementRepoMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ManagementRepositoryImpl implements ManagementRepository {

    private final BasePlanPostgresRepository basePlanPostgresRepository;
    private final ZoneRepository zoneRepository;
    private final ManagementRepoMapper managementRepoMapper;

    public ManagementRepositoryImpl(BasePlanPostgresRepository basePlanPostgresRepository, ZoneRepository zoneRepository, ManagementRepoMapper managementRepoMapper) {
        this.basePlanPostgresRepository = basePlanPostgresRepository;
        this.zoneRepository = zoneRepository;
        this.managementRepoMapper = managementRepoMapper;
    }

    @Override
    public BasePlan save(BasePlan basePlan) {
        List<ZoneEntity> zones = zoneRepository.findZonesByBasePlanId(basePlan.getId().value());
        return managementRepoMapper.basePlanEntityToDomain(
                basePlanPostgresRepository.save(managementRepoMapper.basePlanToEntity(basePlan, zones)));
    }

    @Override
    @Cacheable(value = "basePlansByDate", key = "#from.toString() + '-' + #to.toString()")
    public List<BasePlanEntity> findEventsFromDates(LocalDateTime from, LocalDateTime to) {
        return basePlanPostgresRepository.findBasePlanByPlanDateRange(from, to);
    }

}
