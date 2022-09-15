package com.dh.serieservice.domain.model;

import com.dh.serieservice.domain.model.dto.ChapterWS;
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
public class Season {
    @Id
    private String id;
    private Integer seasonNumber;
    private String genre;
    private List<Chapter> chapters;
}
