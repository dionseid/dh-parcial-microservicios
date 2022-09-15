package com.dh.serieservice.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class Serie {
    @Id
    private String id; // ID con String se autogenera randomly
    private String name;
    private List<String> genre;
    private List<Season> seasons;

}

