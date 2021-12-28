package com.example.streamservice.bindings;

import com.example.streamservice.model.Order;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;

public interface StreamBindings {

    @Input("order-input-channel")
    KStream<String, Order> inputStream();

    @Output("order-delivery-output-channel")
    KStream<String, Order> takeAwayStream();

    @Output("order-takeaway-output-channel")
    KStream<String, Order> deliveryStream();
}
