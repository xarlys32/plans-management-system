package com.fever.plans_management_system.plans_provider.infrastructure.repository;

import com.fever.plans_management_system.plans_provider.application.command.ProcessPlanEventCommand;
import com.fever.plans_management_system.plans_provider.application.handler.ProcessPlanEventCommandHandler;
import com.fever.plans_management_system.plans_provider.infrastructure.repository.xml.entity.PlanListXml;
import com.fever.plans_management_system.plans_provider.infrastructure.repository.xml.exceptions.InvalidXmlDateException;
import com.fever.plans_management_system.plans_provider.infrastructure.repository.xml.mapper.ProviderExtractorMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.format.DateTimeParseException;

@Slf4j
@Configuration
public class ProviderExtractorScheduler {

    @Value("${fever.event.provider.api}")
    private String providerUrl;

    private final ProviderExtractorMapper providerExtractorMapper;
    private final ProcessPlanEventCommandHandler processPlanEventCommandHandler;

    public ProviderExtractorScheduler(ProviderExtractorMapper providerExtractorMapper, ProcessPlanEventCommandHandler processPlanEventCommandHandler) {
        this.providerExtractorMapper = providerExtractorMapper;
        this.processPlanEventCommandHandler = processPlanEventCommandHandler;
    }

    private final WebClient webClient = WebClient.create();

    @Scheduled(fixedDelayString = "${fever.event.provider.fetch-interval-ms}")
    public void fetchPlans() {
        log.info("Fetching plans from external provider...");

        webClient.get()
                .uri(providerUrl+"/api/events")
                .retrieve()
                .bodyToMono(PlanListXml.class)
                .doOnError(error -> log.error("Error fetching events", error))
                .onErrorResume(error -> Mono.empty())
                .subscribe(planListXml ->  {
                    log.info("Fetched XML, forwarding to domain service.");
                    processEventsFromXml(planListXml);
                });
    }

    private void processEventsFromXml(PlanListXml planListXml) {
        planListXml.getOutput().getBasePlans().forEach(basePlanXml -> {
            log.info("Processing plan: {}", basePlanXml.getBasePlanId());
            planListXml.getOutput().getBasePlans().forEach(basePlan -> {
                ProcessPlanEventCommand command = new ProcessPlanEventCommand(
                providerExtractorMapper.basePlanListXmlToRecord(basePlan).orElseThrow(InvalidXmlDateException::new));
                processPlanEventCommandHandler.processAndPublishPlan(command);

            });
        });
    }

}
