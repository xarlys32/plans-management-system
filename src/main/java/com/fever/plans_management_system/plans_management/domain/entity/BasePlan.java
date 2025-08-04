package com.fever.plans_management_system.plans_management.domain.entity;

import com.fever.plans_management_system.plans_provider.domain.event.CreateBasePlanEvent;
import com.fever.plans_management_system.plans_provider.domain.valueobject.BasePlanId;

import java.util.List;

public class BasePlan {
    private BasePlanId id;
    private String title;
    private String sellMode;
    private Long organizerCompanyId;
    private List<Plan> planList;

    public List<Plan> getPlanList() {
        return planList;
    }

    private BasePlan(Builder builder) {
        id = builder.id;
        title = builder.title;
        sellMode = builder.sellMode;
        organizerCompanyId = builder.organizerCompanyId;
        planList = builder.planList;
    }

    public CreateBasePlanEvent validateAndCreateEvent() {
        return new CreateBasePlanEvent("CREATE_BASE_PLAN", this);
    }


    public static Builder builder() {
        return new Builder();
    }

    public BasePlanId getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSellMode() {
        return sellMode;
    }

    public Long getOrganizerCompanyId() {
        return organizerCompanyId;
    }

    public static final class Builder {
        private BasePlanId id;
        private String title;
        private String sellMode;
        private Long organizerCompanyId;
        private List<Plan> planList;

        private Builder() {
        }

        public Builder id(BasePlanId val) {
            id = val;
            return this;
        }

        public Builder title(String val) {
            title = val;
            return this;
        }

        public Builder sellMode(String val) {
            sellMode = val;
            return this;
        }

        public Builder organizerCompanyId(Long val) {
            organizerCompanyId = val;
            return this;
        }

        public Builder planList(List<Plan> val) {
            planList = val;
            return this;
        }

        public BasePlan build() {
            return new BasePlan(this);
        }
    }
}

