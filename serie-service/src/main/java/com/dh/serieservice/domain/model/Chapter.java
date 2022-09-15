package com.dh.serieservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@Builder @Data
@Document
@NoArgsConstructor
public class Chapter {
    private String id;
    private String name;
    private Integer number;
    private String urlStream;
}
