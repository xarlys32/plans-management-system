package com.fever.plans_management_system.plans_management.infrastructure.repository;

import com.fever.plans_management_system.plans_management.application.port.out.ManagementRepository;
import com.fever.plans_management_system.plans_management.domain.entity.BasePlan;
import com.fever.plans_management_system.plans_management.infrastructure.repository.postgres.ManagementPostgresRepository;
import com.fever.plans_management_system.plans_management.infrastructure.repository.postgres.mapper.ManagementRepoMapper;
import org.springframework.stereotype.Service;

@Service
public class ManagementRepositoryImpl implements ManagementRepository {

    private final ManagementPostgresRepository managementPostgresRepository;
    private final ManagementRepoMapper managementRepoMapper;

    public ManagementRepositoryImpl(ManagementPostgresRepository managementPostgresRepository, ManagementRepoMapper managementRepoMapper) {
        this.managementPostgresRepository = managementPostgresRepository;
        this.managementRepoMapper = managementRepoMapper;
    }

    @Override
    public BasePlan save(BasePlan basePlan) {
        return null;
    }
}
