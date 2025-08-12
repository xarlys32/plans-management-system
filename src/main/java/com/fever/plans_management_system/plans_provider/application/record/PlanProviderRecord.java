package com.fever.plans_management_system.plans_provider.application.record;

import java.time.LocalDateTime;
import java.util.List;

public record PlanProviderRecord(
        Long id,
        LocalDateTime startDate,
        LocalDateTime endDate,
        LocalDateTime sellFrom,
        LocalDateTime sellTo,
        boolean soldOut,
        List<ZoneProviderRecord> zones
) {
}
