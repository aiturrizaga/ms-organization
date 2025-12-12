package com.sisuz.organization.model.request;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record TenantCompanyRequest(
        @NotNull
        UUID tenantId,

        @NotNull
        UUID companyId,

        Boolean isActive
) {
}
