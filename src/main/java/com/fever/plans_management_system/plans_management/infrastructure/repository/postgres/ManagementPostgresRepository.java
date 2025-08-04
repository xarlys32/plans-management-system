package com.fever.plans_management_system.plans_management.infrastructure.repository.postgres;

import com.fever.plans_management_system.plans_management.infrastructure.repository.postgres.entity.BasePlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ManagementPostgresRepository extends JpaRepository<BasePlanEntity, Long> {

}
