package com.fever.plans_management_system.plans_provider.domain.entity;

import com.fever.plans_management_system.plans_provider.domain.event.CreateBasePlanEvent;
import com.fever.plans_management_system.plans_provider.domain.valueobject.BasePlanProviderId;

import java.util.List;

public class BasePlanProvider {
    private BasePlanProviderId id;
    private String title;
    private String sellMode;
    private Long organizerCompanyId;
    private List<PlanProvider> planProviderList;

    public List<PlanProvider> getPlanList() {
        return planProviderList;
    }

    private BasePlanProvider(Builder builder) {
        id = builder.id;
        title = builder.title;
        sellMode = builder.sellMode;
        organizerCompanyId = builder.organizerCompanyId;
        planProviderList = builder.planProviderList;
    }

    public CreateBasePlanEvent validateAndCreateEvent() {
        return new CreateBasePlanEvent("CREATE_BASE_PLAN", this);
    }


    public static Builder builder() {
        return new Builder();
    }

    public BasePlanProviderId getId() {
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
        private BasePlanProviderId id;
        private String title;
        private String sellMode;
        private Long organizerCompanyId;
        private List<PlanProvider> planProviderList;

        private Builder() {
        }

        public Builder id(BasePlanProviderId val) {
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

        public Builder planList(List<PlanProvider> val) {
            planProviderList = val;
            return this;
        }

        public BasePlanProvider build() {
            return new BasePlanProvider(this);
        }
    }
}

