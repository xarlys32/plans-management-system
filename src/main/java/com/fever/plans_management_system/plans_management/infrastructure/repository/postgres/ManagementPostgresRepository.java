package com.fever.plans_management_system.plans_management.infrastructure.repository.postgres;

import com.fever.plans_management_system.plans_management.infrastructure.repository.postgres.entity.BasePlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface ManagementPostgresRepository extends JpaRepository<BasePlanEntity, Long> {
    @Query("""
           SELECT DISTINCT bp
           FROM BasePlanEntity bp
           JOIN bp.plans p
           WHERE p.planStartDate >= :from
             AND p.planEndDate <= :to
           """)
    List<BasePlanEntity> findBasePlanByPlanDateRange(@Param("from") LocalDateTime from,
                                             @Param("to") LocalDateTime to);
}
