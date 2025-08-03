package com.fever.plans_management_system.plans_provider.infrastructure.repository.xml.mapper;

import com.fever.plans_management_system.plans_provider.domain.entity.BasePlan;
import com.fever.plans_management_system.plans_provider.domain.valueobject.BasePlanId;
import com.fever.plans_management_system.plans_provider.infrastructure.repository.xml.entity.BasePlanXml;
import org.springframework.stereotype.Component;


@Component
public class ProviderExtractorMapper {
    public BasePlan planListXmlToDomain(BasePlanXml basePlanXml) {
        return BasePlan.builder()
                .id(new BasePlanId(basePlanXml.getBasePlanId()))

                .build();
    }
}
