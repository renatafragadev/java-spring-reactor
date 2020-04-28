package com.fourall.phi.pocks.reactor.rabbitmq.routerloyaltypock.integrations.utils;

import com.fourall.phi.pocks.reactor.rabbitmq.routerloyaltypock.integrations.utils.components.MessageValidator;
import com.fourall.phi.pocks.reactor.rabbitmq.routerloyaltypock.integrations.utils.enums.LogEnum;
import com.fourall.phi.pocks.reactor.rabbitmq.routerloyaltypock.integrations.utils.exception.MessageValidationException;
import com.rabbitmq.client.Delivery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.rabbitmq.AcknowledgableDelivery;
import reactor.rabbitmq.OutboundMessage;
import reactor.rabbitmq.Receiver;
import reactor.rabbitmq.Sender;

@Slf4j
@RequiredArgsConstructor
public abstract class AbstractConsumer {

    private final Receiver receiver;
    private final Sender sender;

    private final MessageValidator messageValidator;

    protected void receiveAndSend(String queue, String exchange, String routingKey) {
        receiver.consumeManualAck(queue)
                .subscribe(message -> {
                    log.info(LogEnum.INFO_CONSUMING.getValue(), message.getProperties().getMessageId(), queue);
                    subscribeHandler(message, exchange, routingKey);
                });
    }

    protected Flux<OutboundMessage> converterToOutboundMessage(Delivery delivery, String exchange, String routingKey) {
        return Flux.just(new OutboundMessage(exchange, routingKey, delivery.getProperties(), delivery.getBody()));
    }

    private void subscribeHandler(AcknowledgableDelivery message, String exchange, String routingKey) {
        try {
            messageValidator.validate(message);
            send(message, exchange, routingKey);
        } catch (MessageValidationException ex) {
            message.nack(false);
        }
    }

    private void send(AcknowledgableDelivery message, String exchange, String routingKey) {
        sender.sendWithPublishConfirms(converterToOutboundMessage(message, exchange, routingKey))
                .subscribe(result -> {
                    if (result.isAck()) {
                        log.info(LogEnum.INFO_PRODUCING.getValue(), message.getProperties().getMessageId(), exchange,
                                routingKey);
                        message.ack();
                    } else {
                        log.error(LogEnum.ERROR_PRODUCING.getValue(), message.getProperties().getMessageId());
                        message.nack(false);
                    }
                });
    }
}