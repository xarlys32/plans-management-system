package com.fever.plans_management_system.plans_provider.infrastructure.repository;

import com.fever.plans_management_system.plans_provider.application.command.ProcessPlanEventCommand;
import com.fever.plans_management_system.plans_provider.application.handler.ProcessPlanEventCommandHandler;
import com.fever.plans_management_system.plans_provider.infrastructure.repository.xml.entity.PlanListXml;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Configuration
public class ProviderExtractorScheduler {

    @Value("${fever.event.provider.api}")
    private String providerUrl;

    private final ProcessPlanEventCommandHandler processPlanEventCommandHandler;

    public ProviderExtractorScheduler(ProcessPlanEventCommandHandler processPlanEventCommandHandler) {
        this.processPlanEventCommandHandler = processPlanEventCommandHandler;
    }


    private final WebClient webClient = WebClient.create(providerUrl);


    @Scheduled(fixedDelayString = "${fever.event.provider.fetch-interval-ms}")
    public void fetchPlans() {
        log.info("Fetching plans from external provider...");

        webClient.get()
                .uri("/api/events")
                .retrieve()
                .bodyToMono(PlanListXml.class)
                .doOnError(error -> log.error("Error fetching events", error))
                .subscribe(xml -> {
                    log.info("Fetched XML, forwarding to domain service.");
                    processPlanEventCommandHandler.processAndPublishPlan(new ProcessPlanEventCommand());
                });
    }

    private void processEventsFromXml(PlanListXml planListXml) {
        planListXml.getOutput().getBasePlans().forEach(basePlanXml -> {});
    }

}
