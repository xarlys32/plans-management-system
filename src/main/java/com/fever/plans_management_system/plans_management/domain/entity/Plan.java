package com.fever.plans_management_system.plans_management.domain.entity;

import com.fever.plans_management_system.plans_provider.domain.valueobject.PlanProviderId;

import java.time.LocalDateTime;
import java.util.List;

public class Plan {
    private PlanProviderId id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime sellFrom;
    private LocalDateTime sellTo;
    private boolean soldOut;
    private List<Zone> zones;

    private Plan(Builder builder) {
        id = builder.id;
        startDate = builder.startDate;
        endDate = builder.endDate;
        sellFrom = builder.sellFrom;
        sellTo = builder.sellTo;
        soldOut = builder.soldOut;
        zones = builder.zones;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private PlanProviderId id;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private LocalDateTime sellFrom;
        private LocalDateTime sellTo;
        private boolean soldOut;
        private List<Zone> zones;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(PlanProviderId val) {
            id = val;
            return this;
        }

        public Builder startDate(LocalDateTime val) {
            startDate = val;
            return this;
        }

        public Builder endDate(LocalDateTime val) {
            endDate = val;
            return this;
        }

        public Builder sellFrom(LocalDateTime val) {
            sellFrom = val;
            return this;
        }

        public Builder sellTo(LocalDateTime val) {
            sellTo = val;
            return this;
        }

        public Builder soldOut(boolean val) {
            soldOut = val;
            return this;
        }

        public Builder zones(List<Zone> val) {
            zones = val;
            return this;
        }

        public Plan build() {
            return new Plan(this);
        }
    }

    public PlanProviderId getId() {
        return id;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public LocalDateTime getSellFrom() {
        return sellFrom;
    }

    public LocalDateTime getSellTo() {
        return sellTo;
    }

    public boolean isSoldOut() {
        return soldOut;
    }

    public List<Zone> getZones() {
        return zones;
    }
}