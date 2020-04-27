package com.fourall.phi.pocks.reactor.rabbitmq.routerloyaltypock.integrations.cashback;

import com.fourall.phi.pocks.reactor.rabbitmq.routerloyaltypock.integrations.utils.AbstractSubscriber;
import com.fourall.phi.pocks.reactor.rabbitmq.routerloyaltypock.integrations.utils.MessageValidator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.rabbitmq.Receiver;
import reactor.rabbitmq.Sender;

@Component
public class CashBackConsumer extends AbstractSubscriber implements CommandLineRunner {

    private final CashBackMessagingPropertyComponent cashBackMessagingProperty;

    public CashBackConsumer(Receiver receiver, Sender sender,
                            MessageValidator messageValidator,
                            CashBackMessagingPropertyComponent cashBackMessagingProperty) {
        super(receiver, sender, messageValidator);
        this.cashBackMessagingProperty = cashBackMessagingProperty;
    }

    @Override
    public void run(String... args) {
        receiveAndSend(cashBackMessagingProperty.getQueueNotify(),
                cashBackMessagingProperty.getExchangeFanout(), "");
    }
}
