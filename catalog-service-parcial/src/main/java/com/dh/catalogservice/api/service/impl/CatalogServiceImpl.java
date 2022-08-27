package com.dh.catalogservice.api.service.impl;

import com.dh.catalogservice.api.service.CatalogService;
import com.dh.catalogservice.domain.model.dto.CatalogWS;
import com.dh.catalogservice.domain.model.dto.MovieWS;
import com.dh.catalogservice.domain.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
@Qualifier("catalogService")
public class CatalogServiceImpl implements CatalogService {

	private final MovieRepository movieRepository;
	private static Logger log = Logger.getLogger(CatalogServiceImpl.class.getName());

	@Autowired
	public CatalogServiceImpl(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	@Override
	public CatalogWS getCatalogByGenre(String genreName) {

		ResponseEntity<List<MovieWS>> movieResponse = movieRepository.getMovieByGenre(genreName);

		log.info("El puerto de movie-service es: " + movieResponse.getHeaders().get("port"));

		if (movieResponse.getStatusCode().is2xxSuccessful() && !movieResponse.getBody().isEmpty())
			return CatalogWS.builder()
					.genre(genreName)
					.movies(movieResponse.getBody())
					.build();

		return null;

	}

}
