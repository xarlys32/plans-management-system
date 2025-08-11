package com.fever.plans_management_system.plans_management.application.handler;

import com.fever.plans_management_system.plans_management.application.command.ProcessMessageCommand;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ProcessMessageCommandHandler {

    @Transactional
    public void processMessageAndPersist(ProcessMessageCommand processMessageCommand) {

    }
}
