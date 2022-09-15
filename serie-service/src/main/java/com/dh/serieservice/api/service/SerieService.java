package com.dh.serieservice.api.service;

import com.dh.serieservice.domain.model.dto.SerieWS;

import java.util.List;

public interface SerieService {
    List<SerieWS> findByAnyGenre(String[] genre);
    SerieWS save(SerieWS serieDTO);
}
