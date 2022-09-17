package com.dh.catalogservice.api.controller;

import com.dh.catalogservice.api.service.CatalogService;
import com.dh.catalogservice.api.service.impl.MovieService;
import com.dh.catalogservice.domain.model.dto.CatalogWS;
import com.dh.catalogservice.domain.model.dto.MovieWS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@RestController
@RequestMapping("/catalogs")
public class CatalogController {

	@Qualifier("catalogService")
	private final CatalogService catalogService;
	private MovieService movieService;
	@Value("${server.port}")
	private String serverPort;

	@Autowired
	public CatalogController(CatalogService catalogService, MovieService movieService) {
		this.catalogService = catalogService;
		this.movieService = movieService;
	}

	@GetMapping("/{genre}")
	ResponseEntity<CatalogWS> findByGenre(@PathVariable String[] genre, HttpServletResponse response) throws Exception {

		response.addHeader("port", serverPort);

		CatalogWS catalogDTO = catalogService.findByGenre(genre);

		if (Objects.isNull(catalogDTO) ||
				(Objects.isNull(catalogDTO.getMovies()) || Objects.isNull(catalogDTO.getSeries())))
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else return new ResponseEntity<>(catalogDTO, HttpStatus.OK);

	}

	/* Que se conecte a la cola de movie para que movie-service la consuma
	catalog-service es producer y movie-service es consumer */
	@PostMapping("/saveMovie")
	public ResponseEntity<String> saveMovie(@RequestBody MovieWS movieDTO) {

		movieService.save(movieDTO);

		return ResponseEntity.ok("The movie was sent to the queue");

	}

}
