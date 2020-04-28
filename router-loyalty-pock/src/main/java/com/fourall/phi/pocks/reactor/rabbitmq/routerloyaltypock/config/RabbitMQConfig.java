package com.fourall.phi.pocks.reactor.rabbitmq.routerloyaltypock.config;

import com.rabbitmq.client.ConnectionFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;
import reactor.rabbitmq.RabbitFlux;
import reactor.rabbitmq.Receiver;
import reactor.rabbitmq.ReceiverOptions;
import reactor.rabbitmq.Sender;
import reactor.rabbitmq.SenderOptions;

@RequiredArgsConstructor
@EnableAutoConfiguration(exclude = RabbitAutoConfiguration.class)
@Configuration
public class RabbitMQConfig {

    private final RabbitMQPropertyComponent rabbitMQProperty;

    @Bean
    public Receiver receiver() {
        ConnectionFactory connectionFactory = rabbitMQProperty.defaultConnection();
        connectionFactory.setVirtualHost(rabbitMQProperty.getOriginVHost());

        return RabbitFlux.createReceiver(new ReceiverOptions()
                .connectionMono(Mono.fromCallable(connectionFactory::newConnection).cache()));
    }

    @Bean
    public Sender sender() {
        ConnectionFactory connectionFactory = rabbitMQProperty.defaultConnection();
        connectionFactory.setVirtualHost(rabbitMQProperty.getDestinyVHost());

        return RabbitFlux.createSender(new SenderOptions()
                .connectionMono(Mono.fromCallable(connectionFactory::newConnection).cache()));
    }
}
