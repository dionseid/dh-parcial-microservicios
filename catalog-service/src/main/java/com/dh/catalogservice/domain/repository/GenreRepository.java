package com.dh.catalogservice.domain.repository;

import com.dh.catalogservice.domain.model.Genre;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends MongoRepository<String, Genre> {
}
