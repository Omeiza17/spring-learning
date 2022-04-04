package com.example.domaincrawler.service;

import com.example.domaincrawler.model.NearEarthObject;
import com.example.domaincrawler.model.NearEarthObjects;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Log4j2
@Service
public class DomainCrawlerService {
    private final KafkaTemplate<String, NearEarthObject> kafkaTemplate;
    private static final String KAFKA_TOPIC = "asteroids";
    private static final String NEOWSAPP_API = "https://www.neowsapp.com/rest/v1/neo/browse?page=0&size=20&api_key=%s";

    @Value("${neowsapp.api-key}")
    private String apiKey;

    public DomainCrawlerService(KafkaTemplate<String, NearEarthObject> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void crawl() {

      WebClient.create().get()
                .uri(String.format(NEOWSAPP_API, apiKey))
                .accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(NearEarthObjects.class)
                .subscribe(domainList -> domainList.getNearEarthObjects().forEach(domain -> kafkaTemplate.send(KAFKA_TOPIC, domain)));

    }
}
