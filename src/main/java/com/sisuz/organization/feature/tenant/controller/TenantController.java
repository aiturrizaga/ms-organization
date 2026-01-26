package com.sisuz.organization.feature.tenant.controller;

import com.sisuz.organization.common.api.ApiResponse;
import com.sisuz.organization.feature.tenant.controller.dto.TenantCreateRequest;
import com.sisuz.organization.feature.tenant.controller.dto.TenantUpdateRequest;
import com.sisuz.organization.feature.tenant.controller.dto.TenantResponse;
import com.sisuz.organization.feature.tenant.service.TenantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Validated
@RestController
@RequestMapping("/v1/tenants")
@RequiredArgsConstructor
public class TenantController {
    private final TenantService tenantService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<TenantResponse> create(@Valid @RequestBody TenantCreateRequest request) {
        return ApiResponse.success("Tenant created", tenantService.create(request));
    }

    @GetMapping("/{id}")
    public ApiResponse<TenantResponse> getById(@PathVariable UUID id) {
        return ApiResponse.success(tenantService.getById(id));
    }

    @GetMapping
    public ApiResponse<Page<TenantResponse>> getAll(Pageable pageable) {
        return ApiResponse.success(tenantService.getAll(pageable));
    }

    @PutMapping("/{id}")
    public ApiResponse<TenantResponse> update(@PathVariable UUID id, @Valid @RequestBody TenantUpdateRequest request) {
        return ApiResponse.success("Tenant updated", tenantService.update(id, request));
    }

    @PatchMapping("/{id}/deactivate")
    public ApiResponse<Void> deactivate(@PathVariable UUID id) {
        tenantService.deactivate(id);
        return ApiResponse.success("Tenant deactivated", null);
    }

    @PatchMapping("/{id}/activate")
    public ApiResponse<Void> activate(@PathVariable UUID id) {
        tenantService.activate(id);
        return ApiResponse.success("Tenant activated", null);
    }

}
