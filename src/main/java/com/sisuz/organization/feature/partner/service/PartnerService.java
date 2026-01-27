package com.sisuz.organization.feature.partner.service;

import com.sisuz.organization.feature.partner.controller.dto.PartnerCreateRequest;
import com.sisuz.organization.feature.partner.controller.dto.PartnerFilter;
import com.sisuz.organization.feature.partner.controller.dto.PartnerResponse;
import com.sisuz.organization.feature.partner.controller.dto.PartnerUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PartnerService {

    PartnerResponse create(PartnerCreateRequest request);

    PartnerResponse getById(Long id);

    Page<PartnerResponse> getAll(Pageable pageable, PartnerFilter filter);

    PartnerResponse update(Long id, PartnerUpdateRequest request);

    void deactivate(Long id);

    void activate(Long id);
}
