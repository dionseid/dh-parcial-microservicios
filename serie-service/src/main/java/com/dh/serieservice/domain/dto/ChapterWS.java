package com.dh.serieservice.domain.dto;

import lombok.Builder;
import lombok.Data;

@Builder @Data
public class ChapterWS {
    private Integer id;
    private String name;
    private Integer number;
    private String urlStream;
}
