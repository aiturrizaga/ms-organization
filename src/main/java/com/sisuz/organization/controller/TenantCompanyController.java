package com.sisuz.organization.controller;

import com.sisuz.organization.model.request.TenantCompanyRequest;
import com.sisuz.organization.model.response.TenantCompanyResponse;
import com.sisuz.organization.service.TenantCompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/tenant-companies")
@RequiredArgsConstructor
public class TenantCompanyController {

    private final TenantCompanyService tenantCompanyService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TenantCompanyResponse createTenantCompany(@Valid @RequestBody TenantCompanyRequest request) {
        return tenantCompanyService.create(request);
    }

    @GetMapping("/by-tenant/{tenantId}")
    public Page<TenantCompanyResponse> getByTenant(@PathVariable UUID tenantId, Pageable pageable) {
        return tenantCompanyService.getByTenant(tenantId, pageable);
    }

    @GetMapping("/by-tenant/{tenantId}/and-company/{companyId}")
    public TenantCompanyResponse getTenantCompany(@PathVariable UUID tenantId, @PathVariable UUID companyId) {
        return tenantCompanyService.getById(tenantId, companyId);
    }

}
