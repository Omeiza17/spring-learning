package com.example.domaincrawler.service;

import com.example.domaincrawler.model.Domain;
import com.example.domaincrawler.model.DomainList;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class DomainCrawlerService {
    private final KafkaTemplate<String, Domain> kafkaTemplate;
    private static final String KAFKA_TOPIC = "web-domains";

    public DomainCrawlerService(KafkaTemplate<String, Domain> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public String crawl(String name) {

        Mono<DomainList> domainListMono = WebClient.create().get()
                .uri(String.format("https://api.domainsdb.info/v1/domains/search?domain=%s&zone=com", name))
                .accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(DomainList.class);

        domainListMono
                .subscribe(domainList -> domainList.getDomains().forEach(domain -> kafkaTemplate.send(KAFKA_TOPIC, domain)));
        return null;
    }
}
