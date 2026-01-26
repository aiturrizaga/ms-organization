package com.sisuz.organization.feature.company.controller;

import com.sisuz.organization.common.api.ApiResponse;
import com.sisuz.organization.feature.company.controller.dto.CompanyCreateRequest;
import com.sisuz.organization.feature.company.controller.dto.CompanyFilter;
import com.sisuz.organization.feature.company.controller.dto.CompanyResponse;
import com.sisuz.organization.feature.company.controller.dto.CompanyUpdateRequest;
import com.sisuz.organization.feature.company.service.CompanyService;
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
@RequiredArgsConstructor
@RequestMapping("/v1/companies")
public class CompanyController {
    private final CompanyService companyService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<CompanyResponse> create(@Valid @RequestBody CompanyCreateRequest request) {
        return ApiResponse.success("Company created", companyService.create(request));
    }

    @GetMapping("/{id}")
    public ApiResponse<CompanyResponse> getById(@PathVariable UUID id) {
        return ApiResponse.success(companyService.getById(id));
    }

    @GetMapping
    public ApiResponse<Page<CompanyResponse>> getAll(Pageable pageable, @ModelAttribute CompanyFilter filter) {
        return ApiResponse.success(companyService.getAll(pageable, filter));
    }

    @PutMapping("/{id}")
    public ApiResponse<CompanyResponse> update(@PathVariable UUID id, @Valid @RequestBody CompanyUpdateRequest request) {
        return ApiResponse.success("Company updated", companyService.update(id, request));
    }

    @PatchMapping("/{id}/deactivate")
    public ApiResponse<Void> deactivate(@PathVariable UUID id) {
        companyService.deactivate(id);
        return ApiResponse.success("Company deactivated", null);
    }

    @PatchMapping("/{id}/activate")
    public ApiResponse<Void> activate(@PathVariable UUID id) {
        companyService.activate(id);
        return ApiResponse.success("Company activated", null);
    }
}
