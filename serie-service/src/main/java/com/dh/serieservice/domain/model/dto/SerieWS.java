package com.dh.serieservice.domain.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Builder @Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class SerieWS {
    private String id;
    private String name;
    private List<String> genre;
    private List<SeasonWS> seasons;
}
