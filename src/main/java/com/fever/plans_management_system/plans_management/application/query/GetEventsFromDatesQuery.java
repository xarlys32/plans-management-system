package com.fever.plans_management_system.plans_management.application.query;

import java.time.LocalDateTime;

public record GetEventsFromDatesQuery(LocalDateTime from, LocalDateTime to) {
}
