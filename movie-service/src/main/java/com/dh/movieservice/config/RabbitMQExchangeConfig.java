package com.dh.movieservice.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQExchangeConfig { // Todos los grupos de mensajes y tópicos se organizan dentro de exchanges

    @Value("${exchange.direct}") // Direct tiene key estricte y es un mensaje. Es tal nombre y no se puede modificar
    private String directExchange;
    @Value("${exchange.fanout}")
    private String fanoutExchange;
    @Value("${exchange.topic}")
    private String topicExchange;

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(directExchange);
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(fanoutExchange);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(topicExchange);
    }

}
