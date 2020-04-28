package com.fourall.phi.pocks.reactor.rabbitmq.routerloyaltypock.integrations.cashback;

import com.fourall.phi.pocks.reactor.rabbitmq.routerloyaltypock.integrations.utils.AbstractConsumer;
import com.fourall.phi.pocks.reactor.rabbitmq.routerloyaltypock.integrations.utils.components.MessageValidator;
import com.fourall.phi.pocks.reactor.rabbitmq.routerloyaltypock.integrations.utils.enums.ExchangeEnum;
import com.fourall.phi.pocks.reactor.rabbitmq.routerloyaltypock.integrations.utils.enums.QueueEnum;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.rabbitmq.Receiver;
import reactor.rabbitmq.Sender;

@Component
public class CashBackConsumer extends AbstractConsumer implements CommandLineRunner {

    public CashBackConsumer(Receiver receiver, Sender sender,
                            MessageValidator messageValidator) {
        super(receiver, sender, messageValidator);
    }

    @Override
    public void run(String... args) {
        receiveAndSend(QueueEnum.CASHBACK.getValue(), ExchangeEnum.CASHBACK_FANOUT.getValue(), "");
    }
}
