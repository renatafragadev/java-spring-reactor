package com.fourall.phi.pocks.reactor.rabbitmq.routerloyaltypock.integrations.utils.enums;

public enum RoutingKeyEnum {

    CREATE("create"),
    UPDATE("update"),
    DISCOUNT_REWARD("discount.reward");

    private final String value;

    RoutingKeyEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
