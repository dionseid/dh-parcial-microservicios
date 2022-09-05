package com.dh.serieservice.api.service;

import com.dh.serieservice.domain.dto.SerieWS;

import java.util.List;

public interface SerieService {
    List<SerieWS> findByGenre(String genre);
}
