package com.fourall.phi.pocks.reactor.rabbitmq.routerloyaltypock.integrations.utils;

import com.rabbitmq.client.Delivery;
import reactor.core.publisher.Flux;
import reactor.rabbitmq.OutboundMessage;

public class OutboundMessageFactory {

    public static Flux<OutboundMessage> converter(Delivery delivery, String exchange, String routingKey) {
        return Flux.just(new OutboundMessage(exchange, routingKey, delivery.getProperties(), delivery.getBody()));
    }
}
