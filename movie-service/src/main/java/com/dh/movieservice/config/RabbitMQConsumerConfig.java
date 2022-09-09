package com.dh.movieservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConsumerConfig {

    @Value("${queue.movie.name}")
    private String movieQueue;

    @Bean
    public Queue queue() { // Linkear la cola que estamos trabajando, que la cree dentro de RabbitMQ y que sea funcional
        return new Queue(this.movieQueue, true);
    }

}
