package com.dh.catalogservice.api.service;

import com.dh.catalogservice.domain.model.dto.CatalogWS;

import javax.servlet.http.HttpServletResponse;

public interface CatalogService {
    public CatalogWS getCatalogByGenre(String genreName);
}
