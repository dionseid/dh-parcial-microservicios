package com.dh.serieservice.api.service.impl;

import com.dh.serieservice.api.service.SerieService;
import com.dh.serieservice.domain.dto.SerieWS;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Qualifier("serieService")
public class SerieServiceImpl implements SerieService {
    @Override
    public List<SerieWS> findByGenre(String genre) {
        return Stream.of(SerieWS.builder().build()).collect(Collectors.toList());
    }
}
