package com.sisuz.organization.model.response;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.UUID;

public record TenantResponse(
        UUID id,
        String name,
        String domain,
        String subdomain,
        String slug,
        JsonNode settings,
        Integer planId,
        Boolean isActive
) {}
