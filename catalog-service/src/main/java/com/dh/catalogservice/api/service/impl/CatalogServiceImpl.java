package com.dh.catalogservice.api.service.impl;

import com.dh.catalogservice.api.service.CatalogService;
import com.dh.catalogservice.domain.model.dto.CatalogWS;
import com.dh.catalogservice.domain.model.dto.MovieWS;
import com.dh.catalogservice.domain.model.dto.SerieWS;
import com.dh.catalogservice.domain.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.logging.Logger;

@Service
@Qualifier("catalogService")
public class CatalogServiceImpl implements CatalogService {

	private static final Logger LOG = Logger.getLogger(CatalogServiceImpl.class.getName());
	private final GenreRepository genreRepository;
	private final MovieService movieService;
	private final SerieService serieService;

	@Autowired
	public CatalogServiceImpl(MovieService movieService, SerieService serieService, GenreRepository genreRepository) {
		this.genreRepository = genreRepository;
		this.movieService = movieService;
		this.serieService = serieService;
	}

	@Override
	public CatalogWS findByGenre(String[] genreName) throws Exception {

		/* Estoy notando que tengo dos APIs a configurar con Circuit Breaker
		El problema está en que las configuraciones del Circuit Breaker no son exactas,
		porque las llamadas no son simultáneas, sino una después de la otra
		Cuando a mí me gustaría que el cliente final reintentase 1 sola vez,
		con las dos APIs caídas tiene que volver a llamar 2 veces más. No es el comportamiento configurado
		Para solventar este problema por sincronismo he implementado el siguiente bloque try/catch */
		List<MovieWS> movies = null;
		List<SerieWS> series = null;

		try {
			movies = movieService.findByGenre(genreName, false/*Boolean.TRUE*/).getBody();
			series = serieService.findByGenre(genreName).getBody();
		} catch (Exception e) {
			if (e.getMessage().contains("MovieClient")) {
				try {

					series = serieService.findByGenre(genreName).getBody();

					throw e;

				} catch (Exception nestedException) {
					if (nestedException.getMessage().contains("SerieClient")) LOG.warning("No API is working");
				}
			} else if (e.getMessage().contains("SerieClient")) {
				try {

					movies = movieService.findByGenre(genreName, false).getBody();

					throw e;

				} catch (Exception nestedException) {
					if (nestedException.getMessage().contains("MovieClient")) LOG.warning("No API is working");
				}
			}
		}

		return CatalogWS.builder()
				.genre(genreName)
				.movies(movies)
				.series(series)
				.build();

	}

}
