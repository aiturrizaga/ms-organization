package com.sisuz.organization.feature.user.controller.dto;

import java.util.UUID;

public record UserCompanyResponse(
        Long id,
        UUID tenantId,
        UUID companyId,
        boolean active
) {
}
