package com.dh.movieservice.api.service;

import com.dh.movieservice.domain.dto.MovieWS;
import com.dh.movieservice.domain.model.Movie;

import java.util.List;

public interface MovieService {

	List<Movie> findByGenre(String genre, Boolean throwError);

	void handleQueueMovieMessageReception(MovieWS movieDTO);

	MovieWS save(MovieWS movieDTO);

	void sendToDirectExchange(MovieWS movieDTO, String routingKey); // direct ≈ routing key

	void sendToFanoutExchange(Movie movie);

	void sendToTopicExchange(Movie movie, String topic);

}
