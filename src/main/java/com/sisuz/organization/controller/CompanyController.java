package com.sisuz.organization.controller;

import com.sisuz.organization.model.request.CompanyRequest;
import com.sisuz.organization.model.response.CompanyResponse;
import com.sisuz.organization.service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompanyResponse createCompany(@Valid @RequestBody CompanyRequest request) {
        return companyService.create(request);
    }

    @GetMapping
    public Page<CompanyResponse> getCompanies(Pageable pageable) {
        return companyService.getAll(pageable);
    }

    @GetMapping("/{companyId}")
    public CompanyResponse getCompany(@PathVariable UUID companyId) {
        return companyService.getById(companyId);
    }

    @PutMapping("/{companyId}")
    public CompanyResponse updateCompany(
            @PathVariable UUID companyId,
            @Valid @RequestBody CompanyRequest request
    ) {
        return companyService.update(companyId, request);
    }
}
