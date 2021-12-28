package com.example.producer.service;

import com.example.producer.model.Order;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.UUID;

@Log4j2
@Service
public class ProducerService {
    private static final String TOPIC = "USER";

    private final KafkaTemplate<String, Order> kafkaTemplate;

    public ProducerService(KafkaTemplate<String, Order> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    public void sendOrderMessage(Order order) {
        log.info("Sending oder [ {} ]", order);
        var sendOrderFuture = kafkaTemplate.send(TOPIC, UUID.randomUUID().toString(), order);
        sendOrderFuture.addCallback(new ListenableFutureCallback<>() {
            @SuppressWarnings("NullableProblems")
            @Override
            public void onFailure(Throwable ex) {
                log.info("Unable to send message [{}] due to : {}", order, ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Order> result) {
                log.info("Sent message [ {} ] with offset [ {} ]", order, result.getRecordMetadata().offset());
            }
        });
    }
}
