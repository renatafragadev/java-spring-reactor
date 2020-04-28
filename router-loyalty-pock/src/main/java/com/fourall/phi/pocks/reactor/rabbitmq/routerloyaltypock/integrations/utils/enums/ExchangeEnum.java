package com.fourall.phi.pocks.reactor.rabbitmq.routerloyaltypock.integrations.utils.enums;

public enum ExchangeEnum {

    CASHBACK_FANOUT("cashback.fx"),
    CASHBACK_DIRECT("cashback.dx"),
    PROMOCODE_TOPIC("promocode.tx");

    private final String value;

    ExchangeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
