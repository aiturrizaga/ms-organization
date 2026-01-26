package com.sisuz.organization.feature.company.service;

import com.sisuz.organization.feature.company.controller.dto.CompanyCreateRequest;
import com.sisuz.organization.feature.company.controller.dto.CompanyFilter;
import com.sisuz.organization.feature.company.controller.dto.CompanyResponse;
import com.sisuz.organization.feature.company.controller.dto.CompanyUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CompanyService {
    CompanyResponse create(CompanyCreateRequest request);

    CompanyResponse getById(UUID id);

    Page<CompanyResponse> getAll(Pageable pageable, CompanyFilter filter);

    CompanyResponse update(UUID id, CompanyUpdateRequest request);

    void deactivate(UUID id);

    void activate(UUID id);
}
