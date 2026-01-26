package com.sisuz.organization.feature.company.controller.dto;

import java.util.UUID;

public record CompanyResponse(
        UUID id,
        String tradeName,
        String legalName,
        String legalOwner,
        Integer documentTypeId,
        String documentNumber,
        Integer billingAddressId,
        Integer legalAddressId,
        Long logoFileId,
        Integer state,
        boolean active
) {
}
