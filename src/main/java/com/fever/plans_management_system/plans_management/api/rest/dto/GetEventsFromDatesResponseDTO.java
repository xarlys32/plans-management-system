package com.fever.plans_management_system.plans_management.api.rest.dto;

import com.fever.plans_management_system.plans_management.application.record.BasePlanRecord;

import java.util.List;

public record GetEventsFromDatesResponseDTO(List<BasePlanRecord> listOfBasePlanRecord){
}
