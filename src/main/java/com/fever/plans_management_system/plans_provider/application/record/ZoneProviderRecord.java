package com.fever.plans_management_system.plans_provider.application.record;

import java.math.BigDecimal;

public record ZoneProviderRecord(
        Long id,
        String name,
        int capacity,
        BigDecimal price,
        boolean isNumbered
){
}
