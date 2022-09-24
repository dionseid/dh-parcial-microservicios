package com.dh.catalogservice.domain.model;

import com.dh.catalogservice.domain.model.dto.MovieWS;
import com.dh.catalogservice.domain.model.dto.SerieWS;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@Builder @Data
@Document
@NoArgsConstructor
public class Genre {
    @Id
    private String id;
    private String name;
    private List<MovieWS> movies;
    private List<SerieWS> series;
}
