package com.fever.plans_management_system.shared.domain.event;

import java.time.Instant;
import java.util.UUID;

public abstract class DomainEvent {
    private final String eventType;
    private final Instant occurredOn;
    private final UUID eventId;

    protected DomainEvent(String eventType) {
        this.eventType = eventType;
        this.occurredOn = Instant.now();
        this.eventId = UUID.randomUUID();
    }

    public String getEventType() {
        return eventType;
    }

    public Instant getOccurredOn() {
        return occurredOn;
    }

    public UUID getEventId() {
        return eventId;
    }
}
