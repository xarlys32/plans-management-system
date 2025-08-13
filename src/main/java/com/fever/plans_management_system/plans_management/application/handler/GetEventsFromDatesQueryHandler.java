package com.fever.plans_management_system.plans_management.application.handler;

import com.fever.plans_management_system.plans_management.application.mapper.ManagementApplicationMapper;
import com.fever.plans_management_system.plans_management.application.port.out.ManagementRepository;
import com.fever.plans_management_system.plans_management.application.query.GetEventsFromDatesQuery;
import com.fever.plans_management_system.plans_management.application.record.BasePlanRecord;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetEventsFromDatesQueryHandler {
    private final ManagementRepository managementRepository;
    private final ManagementApplicationMapper managementApplicationMapper;


    public GetEventsFromDatesQueryHandler(ManagementRepository managementRepository, ManagementApplicationMapper managementApplicationMapper) {
        this.managementRepository = managementRepository;
        this.managementApplicationMapper = managementApplicationMapper;
    }

    public List<BasePlanRecord> getEventsFromDates(GetEventsFromDatesQuery getEventsFromDatesQuery) {
        return managementApplicationMapper.listOfBasePlanEntitiesToRecords(
                managementRepository.findEventsFromDates(getEventsFromDatesQuery.from(), getEventsFromDatesQuery.to()));
    }
}
