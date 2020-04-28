package com.fourall.phi.pocks.reactor.rabbitmq.routerloyaltypock.config;

import com.rabbitmq.client.ConnectionFactory;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqPropertyComponent {

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.port}")
    private Integer port;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Getter
    @Value("${spring.rabbitmq.vhosts.origin}")
    private String originVHost;

    @Getter
    @Value("${spring.rabbitmq.vhosts.destiny}")
    private String destinyVHost;

    public ConnectionFactory defaultConnection() {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        return connectionFactory;
    }
}
