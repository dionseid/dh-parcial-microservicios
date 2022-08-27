package com.dh.catalogservice.domain.repository;

import com.dh.catalogservice.config.FeignConfiguration;
import com.dh.catalogservice.domain.model.dto.MovieWS;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "movie-service")
@LoadBalancerClient(name = "movie-service", configuration = FeignConfiguration.class)
public interface MovieRepository {
    @GetMapping("movies/{genre}")
    ResponseEntity<List<MovieWS>> getMovieByGenre(@PathVariable String genre);
}
