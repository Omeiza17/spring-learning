package com.example.producer.controller;

import com.example.producer.model.Order;
import com.example.producer.service.ProducerService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/kafka")
public class ProducerController {

    private final ProducerService producerService;

    public ProducerController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @RequestMapping("/publish")
    public void publish(@RequestBody Order order) {
        this.producerService.sendOrderMessage(order);
    }
}
