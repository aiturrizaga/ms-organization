package com.sisuz.organization.feature.user.controller.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UserAssignCompanyRequest(
        @NotNull UUID companyId
) {
}
