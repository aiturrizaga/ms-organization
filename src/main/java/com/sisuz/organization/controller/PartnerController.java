package com.sisuz.organization.controller;

import com.sisuz.organization.model.request.PartnerRequest;
import com.sisuz.organization.model.response.PartnerResponse;
import com.sisuz.organization.service.PartnerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/partners")
@RequiredArgsConstructor
public class PartnerController {

    private final PartnerService partnerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PartnerResponse createPartner(@Valid @RequestBody PartnerRequest request) {
        return partnerService.create(request);
    }

    @GetMapping("/by-tenant/{tenantId}")
    public Page<PartnerResponse> getPartnersByTenant(@PathVariable UUID tenantId, Pageable pageable) {
        return partnerService.getByTenant(tenantId, pageable);
    }

    @GetMapping("/by-company/{companyId}")
    public Page<PartnerResponse> getPartnersByCompany(@PathVariable UUID companyId, Pageable pageable) {
        return partnerService.getByCompany(companyId, pageable);
    }

    @GetMapping("/{partnerId}")
    public PartnerResponse getPartner(@PathVariable Long partnerId) {
        return partnerService.getById(partnerId);
    }

    @PutMapping("/{partnerId}")
    public PartnerResponse updatePartner(
            @PathVariable Long partnerId,
            @Valid @RequestBody PartnerRequest request
    ) {
        return partnerService.update(partnerId, request);
    }
}
