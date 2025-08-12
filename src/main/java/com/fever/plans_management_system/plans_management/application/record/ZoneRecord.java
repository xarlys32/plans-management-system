package com.fever.plans_management_system.plans_management.application.record;

import java.math.BigDecimal;

public record ZoneRecord(
        Long id,
        String name,
        int capacity,
        BigDecimal price,
        boolean isNumbered
){
}
