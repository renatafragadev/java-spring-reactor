package com.fourall.phi.pocks.reactor.rabbitmq.routerloyaltypock.integrations.utils;

import com.fourall.phi.pocks.reactor.rabbitmq.routerloyaltypock.integrations.utils.exception.MessageValidationException;
import com.rabbitmq.client.Delivery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.rabbitmq.Receiver;
import reactor.rabbitmq.Sender;

@Slf4j
@RequiredArgsConstructor
public abstract class AbstractSubscriber {

    protected final Receiver receiver;
    protected final Sender sender;
    protected final MessageValidator messageValidator;

    protected void receiveAndSend(String consumeQueue, String producerExchange, String producerRoutingKey) {
        receiver.consumeAutoAck(consumeQueue)
                .doOnError(error -> log.error(MessageLogEnum.ERROR_CONSUMING.getValue(), error.getMessage()))
                .subscribe(message -> {
                    subscribeHandler(message, producerExchange, producerRoutingKey);
                });
    }

    protected void subscribeHandler(Delivery message, String producerExchange, String producerRoutingKey) {
        try {
            messageValidator.validate(message);
            sender.sendWithPublishConfirms(OutboundMessageFactory.converter(message, producerExchange,
                    producerRoutingKey))
                    .subscribe(msgResult -> {
                        if (msgResult.isAck()) {
                            log.info(MessageLogEnum.INFO_PRODUCING_ROUTING_KEY.getValue(),
                                    message.getProperties().getMessageId(), producerExchange, producerRoutingKey);
                        } else {
                            log.error(MessageLogEnum.ERROR_PRODUCING.getValue(),
                                    message.getProperties().getMessageId());
                            // deadletter
                        }
                    });

        } catch (MessageValidationException ex) {
            // deadletter exchange
        }
    }
}
