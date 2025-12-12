package com.sisuz.organization.service;

import com.sisuz.organization.model.request.TenantRequest;
import com.sisuz.organization.model.response.TenantResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface TenantService {
    TenantResponse create(TenantRequest request);

    Page<TenantResponse> getAll(Pageable pageable);

    TenantResponse getById(UUID tenantId);
}
