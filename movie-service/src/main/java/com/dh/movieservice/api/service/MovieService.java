package com.dh.movieservice.api.service;

import com.dh.movieservice.domain.model.Movie;

import java.util.List;

public interface MovieService {

	List<Movie> findByGenre(String genre, Boolean throwError);

	void save(Movie movie);

}
