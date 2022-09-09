package com.dh.movieservice.config;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitTemplateConfig {
    // La dejamos preparada para el caso de que movie-service se conecte con algún otro servicio

    @Bean // Convierte a JSON el mensaje que vamos a estar enviando
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean // Luego vamos a hacer uso de esta dependencia vía inyección
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        // Estamos definiendo que RabbitTemplate convierta y desconvierta tode a JSON
        return rabbitTemplate;
    }

}
