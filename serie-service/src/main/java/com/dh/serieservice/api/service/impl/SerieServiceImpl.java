package com.dh.serieservice.api.service.impl;

import com.dh.serieservice.api.service.SerieService;
import com.dh.serieservice.domain.model.Serie;
import com.dh.serieservice.domain.model.dto.SerieWS;
import com.dh.serieservice.domain.repository.SerieRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Qualifier("serieService")
public class SerieServiceImpl implements SerieService {

    ModelMapper modelMapper = new ModelMapper();
    private final SerieRepository serieRepository;

    @Autowired
    public SerieServiceImpl(SerieRepository serieRepository) {
        this.serieRepository = serieRepository;
    }

    @Override
    public List<SerieWS> findByAnyGenre(String[] genre) {
        List<SerieWS> seriesToReturn = new ArrayList<>();
        List<Serie> seriesFound = serieRepository.findAllByAnyGenre(genre);
        seriesFound.forEach(serie -> seriesToReturn.add(modelMapper.map(serie, SerieWS.class)));
        return seriesToReturn;
    }

    @Override
    public SerieWS save(SerieWS serieDTO) {
        Serie serieToSave = serieRepository.save(modelMapper.map(serieDTO, Serie.class));
        return modelMapper.map(serieToSave, SerieWS.class);
    }

}
