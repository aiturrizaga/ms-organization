package com.sisuz.organization.service;

import com.sisuz.organization.model.request.CompanyRequest;
import com.sisuz.organization.model.response.CompanyResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CompanyService {
    CompanyResponse create(CompanyRequest request);

    Page<CompanyResponse> getAll(Pageable pageable);

    CompanyResponse getById(UUID companyId);

    CompanyResponse update(UUID companyId, CompanyRequest request);
}
