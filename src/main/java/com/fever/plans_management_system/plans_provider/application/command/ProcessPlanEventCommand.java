package com.fever.plans_management_system.plans_provider.application.command;

import java.time.LocalDateTime;

public record ProcessPlanEventCommand(String id,
                                      String name,
                                      String sellMode,
                                      LocalDateTime availableFrom,
                                      LocalDateTime availableTo){
}
