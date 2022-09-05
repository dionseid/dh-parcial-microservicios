package com.dh.movieservice.api.service.impl;

import com.dh.movieservice.api.service.MovieService;
import com.dh.movieservice.domain.model.Movie;
import com.dh.movieservice.domain.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("movieService")
public class MovieServiceImpl implements MovieService {
	private final MovieRepository movieRepository;

	@Autowired
	public MovieServiceImpl(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	@Override
	public List<Movie> findByGenre(String genre, Boolean throwError) {

		if (throwError) throw new RuntimeException();

		return movieRepository.findAllByGenre(genre);

	}

	@Override
	public Movie save(Movie movie) {
		return movieRepository.save(movie);
	}
}
