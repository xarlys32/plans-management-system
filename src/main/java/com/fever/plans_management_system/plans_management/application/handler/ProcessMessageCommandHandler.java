package com.fever.plans_management_system.plans_management.application.handler;

import com.fever.plans_management_system.plans_management.application.command.ProcessMessageCommand;
import com.fever.plans_management_system.plans_management.application.mapper.ManagementApplicationMapper;
import com.fever.plans_management_system.plans_management.application.port.out.ManagementRepository;
import com.fever.plans_management_system.plans_management.domain.entity.BasePlan;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ProcessMessageCommandHandler {

    private final ManagementApplicationMapper managementApplicationMapper;
    private final ManagementRepository managementRepository;

    public ProcessMessageCommandHandler(ManagementApplicationMapper managementApplicationMapper, ManagementRepository managementRepository) {
        this.managementApplicationMapper = managementApplicationMapper;
        this.managementRepository = managementRepository;
    }

    @Transactional
    public void processMessageAndPersist(ProcessMessageCommand processMessageCommand) {
        // We call to domain for validations
        BasePlan basePlan = managementApplicationMapper.processMessageCommandToBasePlan(processMessageCommand);
        managementRepository.save(basePlan);
    }
}
