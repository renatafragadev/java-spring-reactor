package com.fourall.phi.pocks.reactor.rabbitmq.routerloyaltypock.integrations.utils;

import com.fourall.phi.pocks.reactor.rabbitmq.routerloyaltypock.integrations.utils.exception.MessageValidationException;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Delivery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class MessageValidator {

    @Value("${spring.rabbitmq.vhosts.destiny}")
    private String virtualHostDestiny;

    public void validate(Delivery message) {
        if (Optional.ofNullable(message.getBody()).isEmpty()) {
            throw new MessageValidationException(MessageLogEnum.ERROR_BODY_EMPTY.getValue());

        } else if (Optional.ofNullable(message.getProperties().getMessageId()).isEmpty()) {
            throw new MessageValidationException(MessageLogEnum.ERROR_MESSAGE_ID_REQUIRED.getValue());

        } else if (isValidHeader(message.getProperties())) {
            throw new MessageValidationException(MessageLogEnum.ERROR_VHOST_DESTINY_INVALID.getValue());
        }
    }

    private boolean isValidHeader(AMQP.BasicProperties properties) {
        return Optional.ofNullable(properties.getHeaders()).isPresent()
                && virtualHostDestiny.equals(properties.getHeaders().get(virtualHostDestiny));
    }
}
