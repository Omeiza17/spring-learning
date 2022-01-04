package com.example.domainservice.config;

import com.example.domainservice.model.Domain;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Log4j2
@Configuration
public class DomainKafkaConsumer {

    @Bean
    public Consumer<KStream<String, Domain>> domainService() {
        return kstream -> kstream
                .foreach((key, domain) -> log.info("Domain Consumed [{}] - Status [{}]", domain.getDomain(), domain.isDead()));
    }
}
