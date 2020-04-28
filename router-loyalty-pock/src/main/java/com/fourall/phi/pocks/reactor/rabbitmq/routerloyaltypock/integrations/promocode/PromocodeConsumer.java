package com.fourall.phi.pocks.reactor.rabbitmq.routerloyaltypock.integrations.promocode;

import com.fourall.phi.pocks.reactor.rabbitmq.routerloyaltypock.integrations.utils.AbstractConsumer;
import com.fourall.phi.pocks.reactor.rabbitmq.routerloyaltypock.integrations.utils.components.MessageValidator;
import com.fourall.phi.pocks.reactor.rabbitmq.routerloyaltypock.integrations.utils.enums.ExchangeEnum;
import com.fourall.phi.pocks.reactor.rabbitmq.routerloyaltypock.integrations.utils.enums.QueueEnum;
import com.fourall.phi.pocks.reactor.rabbitmq.routerloyaltypock.integrations.utils.enums.RoutingKeyEnum;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.rabbitmq.Receiver;
import reactor.rabbitmq.Sender;

@Component
public class PromocodeConsumer extends AbstractConsumer implements CommandLineRunner {

    public PromocodeConsumer(Receiver receiver, Sender sender, MessageValidator messageValidator) {
        super(receiver, sender, messageValidator);
    }

    @Override
    public void run(String... args) {
        receiveAndSend(QueueEnum.PROMOCODE.getValue(), ExchangeEnum.PROMOCODE_TOPIC.getValue(),
                RoutingKeyEnum.DISCOUNT_REWARD.getValue());
    }
}
