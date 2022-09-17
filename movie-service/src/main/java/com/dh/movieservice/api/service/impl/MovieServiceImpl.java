package com.dh.movieservice.api.service.impl;

import com.dh.movieservice.api.service.MovieService;
import com.dh.movieservice.domain.dto.MovieWS;
import com.dh.movieservice.domain.model.Movie;
import com.dh.movieservice.domain.repository.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("movieService")
public class MovieServiceImpl implements MovieService {

	@Value("${exchange.direct}")
	private String directExchange;
	@Value("${exchange.fanout}")
	private String fanoutExchange;
	private static final Logger LOG = LoggerFactory.getLogger(MovieServiceImpl.class);
	private final MovieRepository movieRepository;
	private RabbitTemplate rabbitTemplate;
	@Value("${exchange.topic}")
	private String topicExchange;

	@Autowired
	public MovieServiceImpl(MovieRepository movieRepository, RabbitTemplate rabbitTemplate) {
		this.movieRepository = movieRepository;
		this.rabbitTemplate = rabbitTemplate;
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
	@RabbitListener(queues = "${queue.movie.name}") //"${queue.movie}"
	public void handleQueueMovieMessageReception(MovieWS movieDTO) {

		LOG.info("A movie was received via Rabbit " + movieDTO.toString());

		movieRepository.save(Movie.builder()
				.name(movieDTO.getName())
				.genre(movieDTO.getGenre())
				.urlStream(movieDTO.getUrlStream())
				.build());

	}

	@RabbitListener(queues = "${queue.B}")
	public void handleQueueBMessageReception(Movie movie) {
		LOG.info("message received in queue B: " + movie.getName());
	}

	@RabbitListener(queues = "${queue.C}")
	public void handleQueueCMessageReception(Movie movie) {
		LOG.info("message received in queue C: " + movie.getName());
	}

	@RabbitListener(queues = "${queue.D}")
	public void handleQueueDMessageReception(Movie movie) {
		LOG.info("message received in queue D: " + movie.getName());
	}

	@RabbitListener(queues = "${queue.E}")
	public void handleQueueEMessageReception(Movie movie) {
		LOG.info("message received in queue E: " + movie.getName());
	}

	@RabbitListener(queues = "${queue.F}")
	public void handleQueueFMessageReception(Movie movie) {
		LOG.info("message received in queue F: " + movie.getName());
	}

	@Override
	public MovieWS save(MovieWS movieDTO) {

		Movie movieToSave = movieRepository.save
				(Movie.builder()
						.name(movieDTO.getName()).genre(movieDTO.getGenre()).urlStream(movieDTO.getUrlStream())
						.build());

		sendToDirectExchange(movieDTO, "${queue.movie.name}");

		return MovieWS.builder()
				.name(movieToSave.getName()).genre(movieToSave.getGenre()).urlStream(movieToSave.getUrlStream())
				.build();

	}

	@Override
	public void sendToDirectExchange(MovieWS movieDTO, String routingKey) {
		rabbitTemplate.convertAndSend(directExchange, routingKey, movieDTO);
	}

	@Override
	public void sendToFanoutExchange(Movie movie) {
		rabbitTemplate.convertAndSend(fanoutExchange, "", movie); // routingKey is ignored
	}

	@Override
	public void sendToTopicExchange(Movie movie, String topic) {
		rabbitTemplate.convertAndSend(topicExchange, topic, movie);
	}

}
