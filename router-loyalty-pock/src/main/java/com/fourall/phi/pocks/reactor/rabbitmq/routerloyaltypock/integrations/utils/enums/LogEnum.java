package com.fourall.phi.pocks.reactor.rabbitmq.routerloyaltypock.integrations.utils.enums;

public enum LogEnum {

    ERROR_BODY_EMPTY("Body cannot be empty"),
    ERROR_VHOST_DESTINY_INVALID("Invalid Virtual-Host-Destiny"),
    ERROR_MESSAGE_ID_REQUIRED("MessageId required"),
    ERROR_CONSUMING("Failed to consume message. Reason for the error: {}"),
    ERROR_PRODUCING("Error sending message id - {}"),
    INFO_CONSUMING("Consuming message: id - {}, queue - {}"),
    INFO_PRODUCING("Message id - {} sent TO exchange - {} AND routing-key: {}");

    private final String value;

    LogEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
