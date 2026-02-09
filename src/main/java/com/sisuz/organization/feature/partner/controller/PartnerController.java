package com.sisuz.organization.feature.partner.controller;

import com.sisuz.organization.common.api.ApiResponse;
import com.sisuz.organization.feature.partner.controller.dto.PartnerCreateRequest;
import com.sisuz.organization.feature.partner.controller.dto.PartnerFilter;
import com.sisuz.organization.feature.partner.controller.dto.PartnerResponse;
import com.sisuz.organization.feature.partner.controller.dto.PartnerUpdateRequest;
import com.sisuz.organization.feature.partner.service.PartnerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/partners")
public class PartnerController {

    private final PartnerService service;

    @PostMapping
    public ApiResponse<PartnerResponse> create(@Valid @RequestBody PartnerCreateRequest request) {
        return ApiResponse.success("Partner created", service.create(request));
    }

    @GetMapping("/{id}")
    public ApiResponse<PartnerResponse> getById(@PathVariable Long id) {
        return ApiResponse.success(service.getById(id));
    }

    @GetMapping
    public ApiResponse<Page<PartnerResponse>> getAll(Pageable pageable, @ModelAttribute PartnerFilter filter) {
        return ApiResponse.success(service.getAll(pageable, filter));
    }

    @PutMapping("/{id}")
    public ApiResponse<PartnerResponse> update(@PathVariable Long id, @Valid @RequestBody PartnerUpdateRequest request) {
        return ApiResponse.success("Partner updated", service.update(id, request));
    }

    @PatchMapping("/{id}/deactivate")
    public ApiResponse<Void> deactivate(@PathVariable Long id) {
        service.deactivate(id);
        return ApiResponse.success("Partner deactivated", null);
    }

    @PatchMapping("/{id}/activate")
    public ApiResponse<Void> activate(@PathVariable Long id) {
        service.activate(id);
        return ApiResponse.success("Partner activated", null);
    }
}
