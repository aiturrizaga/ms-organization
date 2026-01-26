package com.sisuz.organization.feature.tenant.controller.dto;

import java.util.Map;
import java.util.UUID;

public record TenantResponse(
        UUID id,
        String name,
        Integer planId,
        String domain,
        String subdomain,
        String slug,
        boolean active,
        Map<String, Object> settings
) {
}
