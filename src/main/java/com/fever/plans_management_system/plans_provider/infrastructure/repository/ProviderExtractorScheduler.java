package com.fever.plans_management_system.plans_provider.infrastructure.repository;

import com.fever.plans_management_system.plans_provider.application.command.ProcessPlanEventCommand;
import com.fever.plans_management_system.plans_provider.application.handler.ProcessPlanEventCommandHandler;
import com.fever.plans_management_system.plans_provider.infrastructure.repository.xml.entity.PlanListXml;
import com.fever.plans_management_system.plans_provider.infrastructure.repository.xml.exceptions.InvalidXmlDateException;
import com.fever.plans_management_system.plans_provider.infrastructure.repository.xml.mapper.ProviderExtractorMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.format.DateTimeParseException;

@Slf4j
@Configuration
public class ProviderExtractorScheduler {

    @Value("${fever.event.provider.api}")
    private String providerUrl;
    private static final String PROCESSED_EVENTS_KEY = "processedEvents";

    private final ProviderExtractorMapper providerExtractorMapper;
    private final ProcessPlanEventCommandHandler processPlanEventCommandHandler;
    private final RedisTemplate<String, Object> redisTemplate;

    public ProviderExtractorScheduler(ProviderExtractorMapper providerExtractorMapper, ProcessPlanEventCommandHandler processPlanEventCommandHandler,
                                      RedisTemplate<String, Object> redisTemplate) {
        this.providerExtractorMapper = providerExtractorMapper;
        this.processPlanEventCommandHandler = processPlanEventCommandHandler;
        this.redisTemplate = redisTemplate;
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
                if (isNewBasePlan(basePlan.getBasePlanId(), String.valueOf(basePlan.hashCode()))) {
                    ProcessPlanEventCommand command = new ProcessPlanEventCommand(
                            providerExtractorMapper.basePlanListXmlToRecord(basePlan).orElseThrow(InvalidXmlDateException::new));
                    processPlanEventCommandHandler.processAndPublishPlan(command);
                } else {
                    log.info("Repeated event");
                }
            });
        });
    }

    private boolean isNewBasePlan(Long eventId, String eventHash) {
        String key = PROCESSED_EVENTS_KEY + ":" + eventId;
        String cachedHash = (String) redisTemplate.opsForValue().get(key);

        if (eventHash.equals(cachedHash)) {
            return false;
        }

        redisTemplate.opsForValue().set(key, eventHash);
        return true;
    }

}
