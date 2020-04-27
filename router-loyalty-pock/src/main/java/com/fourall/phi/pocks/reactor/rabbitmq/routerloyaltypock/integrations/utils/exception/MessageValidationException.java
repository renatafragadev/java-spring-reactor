package com.fourall.phi.pocks.reactor.rabbitmq.routerloyaltypock.integrations.utils.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageValidationException extends RuntimeException {

    public MessageValidationException(String message) {
        super(message);
        log.error(message);
    }
}
