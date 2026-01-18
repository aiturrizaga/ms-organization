package com.sisuz.organization.controller;

import com.sisuz.organization.common.api.ApiResponse;
import com.sisuz.organization.model.request.CreatePartnerRequest;
import com.sisuz.organization.model.request.UpdatePartnerRequest;
import com.sisuz.organization.model.response.PartnerResponse;
import com.sisuz.organization.service.PartnerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/partners")
@RequiredArgsConstructor
public class PartnerController {

    private static final String TENANT_HEADER = "Tenant-ID";
    private static final String COMPANY_HEADER = "Company-ID";

    private final PartnerService partnerService;

    @PostMapping
    public ResponseEntity<ApiResponse<PartnerResponse>> create(
            @RequestHeader(TENANT_HEADER) UUID tenantId,
            @RequestHeader(COMPANY_HEADER) UUID companyId,
            @Valid @RequestBody CreatePartnerRequest req
    ) {
        PartnerResponse data = partnerService.create(tenantId, companyId, req);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success("Partner created", data));
    }

    @GetMapping("/{partnerId}")
    public ResponseEntity<ApiResponse<PartnerResponse>> get(
            @RequestHeader(TENANT_HEADER) UUID tenantId,
            @RequestHeader(COMPANY_HEADER) UUID companyId,
            @PathVariable Long partnerId
    ) {
        PartnerResponse data = partnerService.get(tenantId, companyId, partnerId);
        return ResponseEntity.ok(ApiResponse.success(data));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<PartnerResponse>>> list(
            @RequestHeader(TENANT_HEADER) UUID tenantId,
            @RequestHeader(COMPANY_HEADER) UUID companyId,
            @RequestParam(name = "isActive", required = false) Boolean isActive,
            Pageable pageable
    ) {
        Page<PartnerResponse> data = partnerService.list(tenantId, companyId, isActive, pageable);
        return ResponseEntity.ok(ApiResponse.success(data));
    }

    @PutMapping("/{partnerId}")
    public ResponseEntity<ApiResponse<PartnerResponse>> update(
            @RequestHeader(TENANT_HEADER) UUID tenantId,
            @RequestHeader(COMPANY_HEADER) UUID companyId,
            @PathVariable Long partnerId,
            @Valid @RequestBody UpdatePartnerRequest req
    ) {
        PartnerResponse data = partnerService.update(tenantId, companyId, partnerId, req);
        return ResponseEntity.ok(ApiResponse.success("Partner updated", data));
    }

    @DeleteMapping("/{partnerId}")
    public ResponseEntity<ApiResponse<Object>> delete(
            @RequestHeader(TENANT_HEADER) UUID tenantId,
            @RequestHeader(COMPANY_HEADER) UUID companyId,
            @PathVariable Long partnerId
    ) {
        partnerService.delete(tenantId, companyId, partnerId);
        return ResponseEntity.ok(ApiResponse.success("Partner deleted", null));
    }
}
