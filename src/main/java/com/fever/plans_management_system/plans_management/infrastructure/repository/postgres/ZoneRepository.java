package com.fever.plans_management_system.plans_management.infrastructure.repository.postgres;

import com.fever.plans_management_system.plans_management.infrastructure.repository.postgres.entity.ZoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZoneRepository extends JpaRepository<ZoneEntity, Long> {
    @Query("""
    SELECT DISTINCT z 
    FROM BasePlanEntity bp
    JOIN bp.plans p
    JOIN p.zones z
    WHERE bp.id = :basePlanId
""")
    List<ZoneEntity> findZonesByBasePlanId(@Param("basePlanId") Long basePlanId);
}
