package com.fever.plans_management_system.plans_provider.application.record;

import java.util.List;

public record BasePlanProviderRecord(
        Long id,
        String title,
        String sellMode,
        Long organizerCompanyId,
        List<PlanProviderRecord> plans
) {
}
