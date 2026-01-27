package com.sisuz.organization.feature.partner.controller.dto;

import java.time.LocalDate;
import java.util.UUID;

public record PartnerResponse(
        Long id,
        UUID tenantId,
        UUID companyId,
        String type,
        String displayName,
        Integer documentTypeId,
        String documentNumber,
        String firstName,
        String lastName,
        String legalName,
        String tradeName,
        String phone,
        String mobile,
        String email,
        LocalDate importantDate,
        boolean active
) {
}
