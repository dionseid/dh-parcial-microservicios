package com.dh.catalogservice.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder @Data
@NoArgsConstructor
public class MovieWS {
    private String name;
    private String genre;
    private String urlStream;
}
