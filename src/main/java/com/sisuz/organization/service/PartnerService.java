package com.sisuz.organization.service;

import com.sisuz.organization.model.request.PartnerRequest;
import com.sisuz.organization.model.response.PartnerResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface PartnerService {
    PartnerResponse create(PartnerRequest request);

    Page<PartnerResponse> getByTenant(UUID tenantId, Pageable pageable);

    Page<PartnerResponse> getByCompany(UUID companyId, Pageable pageable);

    PartnerResponse getById(Long partnerId);

    PartnerResponse update(Long partnerId, PartnerRequest request);
}
