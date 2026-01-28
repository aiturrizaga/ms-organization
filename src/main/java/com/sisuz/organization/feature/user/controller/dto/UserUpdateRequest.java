package com.sisuz.organization.feature.user.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UserUpdateRequest(
        @Email @Size(max = 120) String email,
        Boolean tourEnabled
) {
}
