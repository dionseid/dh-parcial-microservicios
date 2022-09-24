package com.dh.catalogservice.domain.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder @Data
public class SeasonWS {
    private String id;
    private Integer seasonNumber;
    private String genre;
    private List<ChapterWS> chapters;
}
