package com.sisuz.organization.model.request;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TenantRequest(
        @NotBlank
        @Size(max = 50)
        String name,
        @Size(max = 255)
        String domain,
        @Size(max = 255)
        String subdomain,
        @Size(max = 50)
        String slug,
        JsonNode settings,
        @NotNull
        Integer planId,
        Boolean isActive
) {}