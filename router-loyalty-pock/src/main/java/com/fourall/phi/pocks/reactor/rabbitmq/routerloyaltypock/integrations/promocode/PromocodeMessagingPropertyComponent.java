package com.fourall.phi.pocks.reactor.rabbitmq.routerloyaltypock.integrations.promocode;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Getter
@Lazy
@Component
public class PromocodeMessagingPropertyComponent {

    @Value("${integrations.rabbitmq.promocode.exchange.promocode-tx}")
    private String exchangeTopic;

    @Value("${integrations.rabbitmq.promocode.routing-key.all}")
    private String routingKeyAll;

    @Value("${integrations.rabbitmq.promocode.routing-key.reward}")
    private String routingkeyReward;

    @Value("${integrations.rabbitmq.promocode.routing-key.discount}")
    private String routingKeyDiscount;

    @Value("${integrations.rabbitmq.promocode.queue.promocode-notify}")
    private String queueNotify;

}
