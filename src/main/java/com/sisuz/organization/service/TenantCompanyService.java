package com.sisuz.organization.service;

import com.sisuz.organization.model.request.TenantCompanyRequest;
import com.sisuz.organization.model.response.TenantCompanyResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface TenantCompanyService {
    TenantCompanyResponse create(TenantCompanyRequest request);

    Page<TenantCompanyResponse> getByTenant(UUID tenantId, Pageable pageable);

    TenantCompanyResponse getById(UUID tenantId, UUID companyId);
}
