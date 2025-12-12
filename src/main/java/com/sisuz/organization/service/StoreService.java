package com.sisuz.organization.service;

import com.sisuz.organization.model.request.StoreRequest;
import com.sisuz.organization.model.response.StoreResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface StoreService {
    StoreResponse create(StoreRequest request);
    Page<StoreResponse> getByCompany(UUID companyId, Pageable pageable);
    StoreResponse getById(UUID storeId);
    StoreResponse update(UUID storeId, StoreRequest request);
}
