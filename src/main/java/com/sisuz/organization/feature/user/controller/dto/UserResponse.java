package com.sisuz.organization.feature.user.controller.dto;

import java.util.List;
import java.util.UUID;

public record UserResponse(
        UUID id,
        Long partnerId,
        String email,
        Boolean tourEnabled,
        boolean active,
        List<UserCompanyResponse> companies
) {
}
