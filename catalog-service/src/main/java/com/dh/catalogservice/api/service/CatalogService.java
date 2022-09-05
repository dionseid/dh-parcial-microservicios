package com.dh.catalogservice.api.service;

import com.dh.catalogservice.domain.model.dto.CatalogWS;

public interface CatalogService {
    public CatalogWS getByGenre(String genreName) throws Exception;
}
