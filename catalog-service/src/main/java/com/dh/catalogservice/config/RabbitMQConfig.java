package com.dh.catalogservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${queue.movie.name}")
    private String movieQueue;
    @Value("${queue.serie.name}")
    private String serieQueue;

    @Bean
    public Queue queueMovie() { // Linkear la cola que estamos trabajando, que la cree dentro de RabbitMQ y que sea funcional
        return new Queue(this.movieQueue, true);
    }

    @Bean
    public Queue queueSerie() {
        return new Queue(serieQueue, true);
    }

}
