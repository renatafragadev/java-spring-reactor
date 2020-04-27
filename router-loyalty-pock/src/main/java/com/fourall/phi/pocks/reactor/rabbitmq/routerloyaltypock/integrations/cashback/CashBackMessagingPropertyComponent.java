package com.fourall.phi.pocks.reactor.rabbitmq.routerloyaltypock.integrations.cashback;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Getter
@Lazy
@Component
public class CashBackMessagingPropertyComponent {

    @Value("${integrations.rabbitmq.cashback.exchange.cashback-fx}")
    private String exchangeFanout;

    @Value("${integrations.rabbitmq.cashback.exchange.cashback-dx}")
    private String exchangeDirect;

    @Value("${integrations.rabbitmq.cashback.routing-key.cashback-create}")
    private String routingKeyCreate;

    @Value("${integrations.rabbitmq.cashback.routing-key.cashback-update}")
    private String routingKeyUpdate;

    @Value("${integrations.rabbitmq.cashback.queue.cashback-notify}")
    private String queueNotify;
}
