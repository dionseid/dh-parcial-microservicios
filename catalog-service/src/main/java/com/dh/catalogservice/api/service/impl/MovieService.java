package com.dh.catalogservice.api.service.impl;

import com.dh.catalogservice.domain.model.dto.MovieWS;
import com.dh.catalogservice.domain.repository.MovieClient;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class MovieService {
    // Acá manejaremos la lógica del Circuit Breaker y de los reintentos que podamos tener

    private static final Logger LOG = LoggerFactory.getLogger(MovieService.class);
    private final MovieClient movieClient;
    @Value("${queue.movie.name}")
    private String movieQueue;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public MovieService(MovieClient movieClient, RabbitTemplate rabbitTemplate) {
        this.movieClient = movieClient;
        this.rabbitTemplate = rabbitTemplate;
    }

    @CircuitBreaker(name = "moviesByGenre", fallbackMethod = "moviesByGenreFallback")
    @Retry(name = "moviesByGenre")
    public ResponseEntity<List<MovieWS>> findByGenre(String[] genre, boolean throwError) {

        LOG.info("We're about to get the movies...");

        ResponseEntity<List<MovieWS>> movieResponse = movieClient.findByGenre(genre, throwError);

        LOG.info("The port for movie-service is: " + movieResponse.getHeaders().get("port"));

        if (movieResponse.getStatusCode().is2xxSuccessful()
                && !Objects.requireNonNull(movieResponse.getBody()).isEmpty()) return movieResponse;
        else return null;

    }

    private ResponseEntity<List<MovieWS>> moviesByGenreFallback(CallNotPermittedException exception) {
        // Pasarle como parámetro la excepción que el método padre cuando Circuit Breaker está en estado abierto
        // Devolver películas por defecto || loguear información

        LOG.info("Circuit Breaker for moviesByGenre was activated");

        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);

    }

    public void save(MovieWS movieDto) {
        rabbitTemplate.convertAndSend(movieQueue, movieDto); /* Pasar el nombre de cola o routing key
        Acá estamos usando RabbitTemplate que hemos definide como Bean */
    }


}
