package com.dh.catalogservice.api.controller;

import com.dh.catalogservice.api.service.CatalogService;
import com.dh.catalogservice.domain.model.dto.CatalogWS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@RestController
@RequestMapping("/catalogs")
public class CatalogController {

	@Qualifier("catalogService")
	private final CatalogService catalogService;
	@Value("${server.port}")
	private String serverPort;

	@Autowired
	public CatalogController(CatalogService catalogService) {
		this.catalogService = catalogService;
	}

	@GetMapping("/{genre}")
	ResponseEntity<CatalogWS> findByGenre(@PathVariable String[] genre, HttpServletResponse response) throws Exception {

		response.addHeader("port", serverPort);

		CatalogWS catalogDto = catalogService.findByGenre(genre);

		if (Objects.isNull(catalogDto) ||
				(Objects.isNull(catalogDto.getMovies()) || Objects.isNull(catalogDto.getSeries())))
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else return new ResponseEntity<>(catalogDto, HttpStatus.OK);

	}

}
