package com.dh.serieservice.api.controller;

import com.dh.serieservice.api.service.SerieService;
import com.dh.serieservice.domain.dto.SerieWS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/series")
public class SerieController {

    @Qualifier("serieService")
    private final SerieService serieService;

    @Autowired
    public SerieController(SerieService serieService) {
        this.serieService = serieService;
    }

    @GetMapping("/{genre}")
    public ResponseEntity<List<SerieWS>> findByGenre(@PathVariable String genre,
                                                     @RequestParam(defaultValue = "false") Boolean throwError) {

        List<SerieWS> series = serieService.findByGenre(genre);

        return Objects.isNull(series) || series.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : ResponseEntity.ok().body(series);

    }

}
