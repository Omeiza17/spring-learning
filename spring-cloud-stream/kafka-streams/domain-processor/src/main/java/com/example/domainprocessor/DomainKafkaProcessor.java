package com.example.domainprocessor;

import com.example.domainprocessor.model.Domain;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Log4j2
@Configuration
public class DomainKafkaProcessor {

    @Bean
    public Function<KStream<String, Domain>, KStream<String, Domain>> domainProcessor() {
        return kstream -> kstream.filter((key, domain) -> {
            if (domain.isDead()) {
                log.info("Inactive Domain: ({})", domain.getDomain());
            } else {
                log.info("Active Domain: [{}]", domain.getDomain());
            }
            return !domain.isDead();
        });
    }
}
