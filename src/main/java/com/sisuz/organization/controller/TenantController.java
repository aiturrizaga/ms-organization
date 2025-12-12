package com.sisuz.organization.controller;

import com.sisuz.organization.model.request.TenantRequest;
import com.sisuz.organization.model.response.TenantResponse;
import com.sisuz.organization.service.TenantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/tenants")
@RequiredArgsConstructor
public class TenantController {
    private final TenantService tenantService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TenantResponse createTenant(@Valid @RequestBody TenantRequest request) {
        return tenantService.create(request);
    }

    @GetMapping
    public Page<TenantResponse> getTenants(Pageable pageable) {
        return tenantService.getAll(pageable);
    }

    @GetMapping("/{tenantId}")
    public TenantResponse getTenant(@PathVariable UUID tenantId) {
        return tenantService.getById(tenantId);
    }

}
