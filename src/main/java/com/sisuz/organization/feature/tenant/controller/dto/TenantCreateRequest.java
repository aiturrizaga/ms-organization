package com.sisuz.organization.feature.tenant.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Map;

public record TenantCreateRequest(
        @NotBlank @Size(max = 60) String name,
        @NotNull Integer planId,
        @Size(max = 255) String domain,
        @Size(max = 255) String subdomain,
        @Size(max = 60) String slug,
        Map<String, Object> settings
) {
}
