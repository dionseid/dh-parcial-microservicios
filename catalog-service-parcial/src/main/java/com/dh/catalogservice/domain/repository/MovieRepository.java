package com.dh.catalogservice.domain.repository;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "movie-service")
public interface MovieRepository {
}
