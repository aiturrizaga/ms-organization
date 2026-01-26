package com.sisuz.organization.feature.tenant.service;

import com.sisuz.organization.feature.tenant.controller.dto.TenantCreateRequest;
import com.sisuz.organization.feature.tenant.controller.dto.TenantUpdateRequest;
import com.sisuz.organization.feature.tenant.controller.dto.TenantResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface TenantService {
    TenantResponse create(TenantCreateRequest request);

    TenantResponse getById(UUID id);

    Page<TenantResponse> getAll(Pageable pageable);

    TenantResponse update(UUID id, TenantUpdateRequest request);

    void deactivate(UUID id);

    void activate(UUID id);
}
