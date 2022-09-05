package com.dh.catalogservice.domain.repository;

import com.dh.catalogservice.config.FeignConfiguration;
import com.dh.catalogservice.domain.model.dto.MovieWS;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "movie-service")
@LoadBalancerClient(name = "movie-service", configuration = FeignConfiguration.class)
public interface MovieClient {
    @GetMapping("/movies/{genre}")
    ResponseEntity<List<MovieWS>> findByGenre(@PathVariable String genre, @RequestParam boolean throwError);
        // throwError está hardcodeade para probar le Circuit Breaker
}
