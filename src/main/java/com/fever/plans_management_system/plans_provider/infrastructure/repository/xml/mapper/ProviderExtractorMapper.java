package com.fever.plans_management_system.plans_provider.infrastructure.repository.xml.mapper;

import com.fever.plans_management_system.plans_provider.application.record.BasePlanRecord;
import com.fever.plans_management_system.plans_provider.application.record.PlanRecord;
import com.fever.plans_management_system.plans_provider.application.record.ZoneRecord;
import com.fever.plans_management_system.plans_provider.domain.entity.BasePlan;
import com.fever.plans_management_system.plans_provider.domain.valueobject.BasePlanId;
import com.fever.plans_management_system.plans_provider.infrastructure.repository.xml.entity.BasePlanXml;
import com.fever.plans_management_system.plans_provider.infrastructure.repository.xml.entity.PlanXml;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
public class ProviderExtractorMapper {
    public BasePlanRecord basePlanListXmlToRecord(BasePlanXml basePlanXml) {
        return new BasePlanRecord(
                basePlanXml.getBasePlanId(),
                basePlanXml.getTitle(),
                basePlanXml.getSellMode(),
                basePlanXml.getOrganizerCompanyId(),
                basePlanXml.getPlans().stream()
                        .map(this::planListXmlToRecord).toList()
        );
    }

    private PlanRecord planListXmlToRecord(PlanXml planXml) {
        return new PlanRecord(planXml.getPlanId(),
                formatDateTime(planXml.getStartDate()),
                formatDateTime(planXml.getEndDate()),
                formatDateTime(planXml.getSellFrom()),
                formatDateTime(planXml.getSellTo()),
                planXml.getSoldOut(),
                planXml.getZones().stream().map(zoneXml -> new ZoneRecord(zoneXml.getZoneId(),
                        zoneXml.getName(),
                        zoneXml.getCapacity(),
                        zoneXml.getPrice(),
                        zoneXml.getNumbered())).toList()
        );
    }

    private LocalDateTime formatDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime);
    }

}
