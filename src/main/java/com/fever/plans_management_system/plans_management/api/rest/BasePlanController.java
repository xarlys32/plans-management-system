package com.fever.plans_management_system.plans_management.api.rest;

import com.fever.plans_management_system.plans_management.api.rest.dto.GetEventsFromDatesResponseDTO;
import com.fever.plans_management_system.plans_management.api.rest.mapper.ManagementControllerMapper;
import com.fever.plans_management_system.plans_management.application.handler.GetEventsFromDatesQueryHandler;
import com.fever.plans_management_system.plans_management.application.query.GetEventsFromDatesQuery;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@RestController
@RequestMapping("/events")
public class BasePlanController {

    private final GetEventsFromDatesQueryHandler getEventsFromDatesQueryHandler;
    private final ManagementControllerMapper managementControllerMapper;

    public BasePlanController(GetEventsFromDatesQueryHandler getEventsFromDatesQueryHandler, ManagementControllerMapper managementControllerMapper) {
        this.getEventsFromDatesQueryHandler = getEventsFromDatesQueryHandler;
        this.managementControllerMapper = managementControllerMapper;
    }

    @GetMapping("/")
    @Operation(summary = "Get events from dates")
    public ResponseEntity<GetEventsFromDatesResponseDTO> getEvents(@Parameter(description = "Date from", example = "yyyy-MM-dd")
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @Parameter(description = "Date to", example = "yyyy-MM-dd")
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {

        LocalDateTime fromDateTime = from.atStartOfDay();
        LocalDateTime toDateTime = to.atTime(LocalTime.MAX);

        GetEventsFromDatesResponseDTO dto = managementControllerMapper.listOfBasePlanRecordToResponse(
                getEventsFromDatesQueryHandler.getEventsFromDates(new GetEventsFromDatesQuery(fromDateTime, toDateTime)));

        return ResponseEntity.ok(dto);
    }
}
