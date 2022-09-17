package com.dh.movieservice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder @Data
public class MovieWS {
    private String name;
    private String genre;
    private String urlStream;
}
