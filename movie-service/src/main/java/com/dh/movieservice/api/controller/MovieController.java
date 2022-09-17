package com.dh.movieservice.api.controller;

import com.dh.movieservice.api.service.MovieService;
import com.dh.movieservice.domain.dto.MovieWS;
import com.dh.movieservice.domain.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

	@Qualifier("movieService")
	private final MovieService movieService;
	@Value("${server.port}")
	private String serverPort;

	@Autowired
	public MovieController(MovieService movieService) {
		this.movieService = movieService;
	}

	@GetMapping("/{genre}")
	public ResponseEntity<List<Movie>> findByGenre(@PathVariable String genre,
												   HttpServletResponse response,
												   @RequestParam(defaultValue = "false") Boolean throwError) {

		List<Movie> movies = movieService.findByGenre(genre, throwError);

		response.addHeader("port", serverPort);
		System.out.println("The server port is: " + serverPort);

		return movies.isEmpty()	? new ResponseEntity<>(HttpStatus.NOT_FOUND) : ResponseEntity.ok().body(movies);

	}

	@PostMapping
	public ResponseEntity<MovieWS> save(@RequestBody MovieWS movieDTO) {
		return ResponseEntity.ok().body(movieService.save(movieDTO));
	}

	@PostMapping(value = "/direct/{routingKey}",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity sendToDirectExchange
			(@PathVariable(value = "routingKey") String routingKey, @RequestBody MovieWS movieDTO) {

		movieService.sendToDirectExchange(movieDTO, routingKey);

		return ResponseEntity.status(HttpStatus.OK).build();

	}

	@PostMapping(value = "/fanout",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity sendToFanoutExchange(@RequestBody Movie movie) {

		movieService.sendToFanoutExchange(movie);

		return ResponseEntity.status(HttpStatus.OK).build();

	}

	@PostMapping(value = "/topic/{topic}",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity sendToTopicExchange(@PathVariable(value = "topic") String topic, @RequestBody Movie movie) {

		movieService.sendToTopicExchange(movie, topic);

		return ResponseEntity.status(HttpStatus.OK).build();

	}

//	@PostMapping
//	public /*ResponseEntity<Movie>*/void saveMovie(@RequestBody Movie movie) {
////		return ResponseEntity.ok().body(movieService.save(movie));
//		movieService.save(movie);
//	}

}
