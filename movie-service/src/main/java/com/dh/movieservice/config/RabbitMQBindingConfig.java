package com.dh.movieservice.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQBindingConfig { // Message broker podría, por ejemplo, hacer validaciones de mensajes

    /* Con tipo de mensaje direct envío un mensaje a un route key, a un nombre específico, a un tópico específico
    Es muy rápide porque es un map key */
    @Value("${routing.direct.1}")
    private String directRoutingKey;
    @Value("${routing.direct.2}")
    private String direct2RoutingKey;

    /* Con tipo de mensaje topic puedo usar wildcards o asteriscos para, por ejemplo,
    enviar o recibir mensajes a/de todos los tópicos que empiecen con 'orders'. Este no aplica en otros motores */
    @Value("${routing.topic.rabbitmq.#}")
    private String topicRabbitMQRoutingKey;
    @Value("${routing.topic.rabbitmq.spring.#}")
    private String topicRabbitMQSpringRoutingKey;

    /* Asociar un cola con un método y con un key
    Mandar un mensaje a un cola, con un determinade exchange, con un determinado tópico */

    @Bean
    public Binding bindingDirectExchangeQueueMovieDirect1(DirectExchange directExchange, Queue queueMovie) {
        return BindingBuilder.bind(queueMovie).to(directExchange).with(directRoutingKey);
    }

    @Bean
    public Binding bindingDirectExchangeQueueCDirect2(DirectExchange directExchange, Queue queueB) {
        return BindingBuilder.bind(queueB).to(directExchange).with(direct2RoutingKey);
    }

    /* Fanout es un tipo de comunicación que implica un broadcast a todas las colas; topics con strings con asteriscos;
    o no tenemos topic. Es muy rápide */

    @Bean
    public Binding bindingFanoutExchangeQueueEFanaout(FanoutExchange fanoutExchange, Queue queueE) {
        return BindingBuilder.bind(queueE).to(fanoutExchange);
    }

    @Bean
    public Binding bindingFanoutExchangeQueueFFanout(FanoutExchange fanoutExchange, Queue queueF) {
        return BindingBuilder.bind(queueF).to(fanoutExchange);
    }

    @Bean
    public Binding bindingTopicExchangeQueueCTopicRabbitMQ(TopicExchange topicExchange, Queue queueC) {
        return BindingBuilder.bind(queueC).to(topicExchange).with(topicRabbitMQRoutingKey);
    }

    @Bean
    public Binding bindingTopicExchangeQueueDTopicRabbitMQ(TopicExchange topicExchange, Queue queueD) {
        return BindingBuilder.bind(queueD).to(topicExchange).with(topicRabbitMQSpringRoutingKey);
    }

}
