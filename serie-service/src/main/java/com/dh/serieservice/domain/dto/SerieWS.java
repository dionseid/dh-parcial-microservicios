package com.dh.serieservice.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder @Data
public class SerieWS {
    private Integer id;
    private String name;
    private String genre;
    private List<SeasonWS> seasons;
}
