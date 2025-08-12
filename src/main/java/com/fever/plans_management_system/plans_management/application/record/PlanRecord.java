package com.fever.plans_management_system.plans_management.application.record;

import java.time.LocalDateTime;
import java.util.List;

public record PlanRecord(
        Long id,
        LocalDateTime startDate,
        LocalDateTime endDate,
        LocalDateTime sellFrom,
        LocalDateTime sellTo,
        boolean soldOut,
        List<ZoneRecord> zones
) {
}
