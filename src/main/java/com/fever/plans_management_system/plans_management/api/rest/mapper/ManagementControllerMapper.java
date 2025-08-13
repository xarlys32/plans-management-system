package com.fever.plans_management_system.plans_management.api.rest.mapper;

import com.fever.plans_management_system.plans_management.api.rest.dto.GetEventsFromDatesResponseDTO;
import com.fever.plans_management_system.plans_management.application.record.BasePlanRecord;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManagementControllerMapper {

    public GetEventsFromDatesResponseDTO listOfBasePlanRecordToResponse(List<BasePlanRecord> list) {
        return new GetEventsFromDatesResponseDTO(list);
    }
}
