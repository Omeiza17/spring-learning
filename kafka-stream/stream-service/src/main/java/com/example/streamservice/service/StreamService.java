package com.example.streamservice.service;

import com.example.streamservice.bindings.StreamBindings;
import com.example.streamservice.model.Order;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;

@EnableBinding(StreamBindings.class)
public class StreamService {

    @StreamListener("order-input-channel")
    @SendTo("order-takeaway-output-channel")
    public KStream<String, Order> takeAway(KStream<String, Order> orderStream) {
        return orderStream.filter((s, order) -> order.getDeliveryType().equalsIgnoreCase("takeaway"));
    }

    @StreamListener("order-input-channel")
    @SendTo("order-delivery-output-channel")
    public KStream<String, Order> delivery(KStream<String, Order> orderStream) {
        return orderStream.filter((s, order) -> order.getDeliveryType().equalsIgnoreCase("delivery"));
    }

}
