package com.fever.plans_management_system.plans_management.application.record;

import java.util.List;

public record BasePlanRecord(
        Long id,
        String title,
        String sellMode,
        Long organizerCompanyId,
        List<PlanRecord> plans
) {
}
