package com.sisuz.organization.feature.store.service;

import com.sisuz.organization.feature.store.controller.dto.StoreCreateRequest;
import com.sisuz.organization.feature.store.controller.dto.StoreFilter;
import com.sisuz.organization.feature.store.controller.dto.StoreResponse;
import com.sisuz.organization.feature.store.controller.dto.StoreUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface StoreService {
    StoreResponse create(StoreCreateRequest request);

    StoreResponse getById(UUID id);

    Page<StoreResponse> getAll(Pageable pageable, StoreFilter filter);

    StoreResponse update(UUID id, StoreUpdateRequest request);

    void deactivate(UUID id);

    void activate(UUID id);
}
