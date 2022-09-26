package com.dh.movieservice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder @Data
public class MovieWS {
    private String name;
    private List<String> genre;
    private String urlStream;
}
