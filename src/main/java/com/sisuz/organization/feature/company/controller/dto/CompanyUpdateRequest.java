package com.sisuz.organization.feature.company.controller.dto;

import jakarta.validation.constraints.Size;

public record CompanyUpdateRequest(
        @Size(max = 120) String tradeName,
        @Size(max = 120) String legalName,
        @Size(max = 120) String legalOwner,
        Integer documentTypeId,
        @Size(max = 60) String documentNumber,
        Integer billingAddressId,
        Integer legalAddressId,
        Long logoFileId,
        Integer state
) {
}
