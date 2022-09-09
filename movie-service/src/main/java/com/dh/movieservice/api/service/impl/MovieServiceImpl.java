package com.dh.movieservice.api.service.impl;

import com.dh.movieservice.api.service.MovieService;
import com.dh.movieservice.domain.model.Movie;
import com.dh.movieservice.domain.repository.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("movieService")
public class MovieServiceImpl implements MovieService {

	private static final Logger LOG = LoggerFactory.getLogger(MovieServiceImpl.class);
	private final MovieRepository movieRepository;

	@Autowired
	public MovieServiceImpl(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	@Override
	public List<Movie> findByGenre(String genre, Boolean throwError) {

		LOG.info("We are about to search movies by genre");

		if (throwError) {

			LOG.error("There was an error searching movies by genre");

			throw new RuntimeException();

		}

		return movieRepository.findAllByGenre(genre);

	}

	@Override
	@RabbitListener(queues = "${queue.movie.name}")
	public Movie save(Movie movie) {
		LOG.info("A movie was received via Rabbit " + movie.toString());
		return movieRepository.save(movie);
	}

}
