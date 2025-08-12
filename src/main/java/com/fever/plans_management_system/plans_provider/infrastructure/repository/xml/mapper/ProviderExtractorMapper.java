package com.fever.plans_management_system.plans_provider.infrastructure.repository.xml.mapper;

import com.fever.plans_management_system.plans_provider.application.record.BasePlanProviderRecord;
import com.fever.plans_management_system.plans_provider.application.record.PlanProviderRecord;
import com.fever.plans_management_system.plans_provider.application.record.ZoneProviderRecord;
import com.fever.plans_management_system.plans_provider.infrastructure.repository.xml.entity.BasePlanXml;
import com.fever.plans_management_system.plans_provider.infrastructure.repository.xml.entity.PlanXml;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Optional;


@Component
public class ProviderExtractorMapper {
    public Optional<BasePlanProviderRecord> basePlanListXmlToRecord(BasePlanXml basePlanXml) {
        try {
            return Optional.of(new BasePlanProviderRecord(
                    basePlanXml.getBasePlanId(),
                    basePlanXml.getTitle(),
                    basePlanXml.getSellMode(),
                    basePlanXml.getOrganizerCompanyId(),
                    basePlanXml.getPlans().stream()
                            .map(this::planListXmlToRecord).toList()
            ));
        } catch (DateTimeParseException e) {
           return Optional.empty();
        }
    }

    private PlanProviderRecord planListXmlToRecord(PlanXml planXml)  throws DateTimeParseException {
        return new PlanProviderRecord(planXml.getPlanId(),
                formatDateTime(planXml.getStartDate()),
                formatDateTime(planXml.getEndDate()),
                formatDateTime(planXml.getSellFrom()),
                formatDateTime(planXml.getSellTo()),
                planXml.getSoldOut(),
                planXml.getZones().stream().map(zoneXml -> new ZoneProviderRecord(zoneXml.getZoneId(),
                        zoneXml.getName(),
                        zoneXml.getCapacity(),
                        zoneXml.getPrice(),
                        zoneXml.getNumbered())).toList()
        );
    }

    private LocalDateTime formatDateTime(String dateTime) throws DateTimeParseException{
        return LocalDateTime.parse(dateTime);
    }

}
