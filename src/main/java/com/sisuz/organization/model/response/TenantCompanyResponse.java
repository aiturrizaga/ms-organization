package com.sisuz.organization.model.response;

import java.util.UUID;

public record TenantCompanyResponse(
        UUID tenantId,
        UUID companyId,
        Boolean isActive
) {}
