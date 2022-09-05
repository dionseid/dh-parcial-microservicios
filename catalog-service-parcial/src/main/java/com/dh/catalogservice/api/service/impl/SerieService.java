package com.dh.catalogservice.api.service.impl;

import com.dh.catalogservice.domain.model.dto.SerieWS;
import com.dh.catalogservice.domain.repository.SerieClient;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class SerieService {
    // Acá manejaremos la lógica del Circuit Breaker y de los reintentos que podamos tener

    private static final Logger log = LoggerFactory.getLogger(SerieService.class);
    private final SerieClient serieClient;

    @Autowired
    public SerieService(SerieClient serieClient) {
        this.serieClient = serieClient;
    }

    @CircuitBreaker(name = "seriesByGenre", fallbackMethod = "seriesByGenreFallback")
    @Retry(name = "seriesByGenre")
    public ResponseEntity<List<SerieWS>> findByGenre(String genre) {

        log.info("We're about to get the series...");

        ResponseEntity<List<SerieWS>> serieResponse = serieClient.findByGenre(genre);

        if (serieResponse.getStatusCode().is2xxSuccessful()
                && !Objects.requireNonNull(serieResponse.getBody()).isEmpty()) return serieResponse;
        else return null;

    }

    private ResponseEntity<List<SerieWS>> seriesByGenreFallback(CallNotPermittedException exception) {
        // Pasarle como parámetro la excepción que el método padre cuando Circuit Breaker está en estado abierto
        // Devolver series por defecto || loguear información

        log.info("Circuit Breaker for seriesByGenre was activated");

        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);

    }

}
