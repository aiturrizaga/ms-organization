package com.sisuz.organization.service;

import com.sisuz.organization.model.request.CreatePartnerRequest;
import com.sisuz.organization.model.request.UpdatePartnerRequest;
import com.sisuz.organization.model.response.PartnerResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface PartnerService {
    PartnerResponse create(UUID tenantId, UUID companyId, CreatePartnerRequest req);

    PartnerResponse update(UUID tenantId, UUID companyId, Long partnerId, UpdatePartnerRequest req);

    PartnerResponse get(UUID tenantId, UUID companyId, Long partnerId);

    Page<PartnerResponse> list(UUID tenantId, UUID companyId, Boolean isActive, Pageable pageable);

    void delete(UUID tenantId, UUID companyId, Long partnerId);
}
