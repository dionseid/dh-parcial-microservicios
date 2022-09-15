package com.dh.serieservice.domain.model.dto;

import lombok.Builder;
import lombok.Data;

@Builder @Data
public class ChapterWS {
    private String id;
    private String name;
    private Integer number;
    private String urlStream;
}
