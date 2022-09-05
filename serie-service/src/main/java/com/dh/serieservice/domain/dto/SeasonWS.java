package com.dh.serieservice.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder @Data
public class SeasonWS {
    private Integer id;
    private Integer seasonNumber;
    private String genre;
    private List<ChapterWS> chapters;
}
