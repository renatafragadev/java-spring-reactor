package com.fourall.phi.pocks.reactor.rabbitmq.routerloyaltypock.integrations.promocode;

import com.fourall.phi.pocks.reactor.rabbitmq.routerloyaltypock.integrations.utils.AbstractSubscriber;
import com.fourall.phi.pocks.reactor.rabbitmq.routerloyaltypock.integrations.utils.MessageValidator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.rabbitmq.Receiver;
import reactor.rabbitmq.Sender;

@Component
public class PromocodeConsumer extends AbstractSubscriber implements CommandLineRunner {

    private final PromocodeMessagingPropertyComponent promocodeMessagingProperty;

    public PromocodeConsumer(Receiver receiver, Sender sender,
                             MessageValidator messageValidator,
                             PromocodeMessagingPropertyComponent promocodeMessagingProperty) {
        super(receiver, sender, messageValidator);
        this.promocodeMessagingProperty = promocodeMessagingProperty;
    }

    @Override
    public void run(String... args) {
        receiveAndSend(promocodeMessagingProperty.getQueueNotify(), promocodeMessagingProperty.getExchangeTopic(),
                promocodeMessagingProperty.getRoutingKeyAll());
    }
}
