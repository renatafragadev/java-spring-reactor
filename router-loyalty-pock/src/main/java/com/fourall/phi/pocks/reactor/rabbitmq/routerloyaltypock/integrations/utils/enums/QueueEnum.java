package com.fourall.phi.pocks.reactor.rabbitmq.routerloyaltypock.integrations.utils.enums;

public enum QueueEnum {

    CASHBACK("router-loyalty.cashback.notify"),
    PROMOCODE("router-loyalty.promocode.notify");

    private final String value;

    QueueEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
