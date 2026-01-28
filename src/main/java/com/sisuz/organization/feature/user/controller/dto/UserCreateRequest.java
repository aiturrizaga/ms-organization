package com.sisuz.organization.feature.user.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserCreateRequest(
        @NotNull Long partnerId,
        @NotBlank @Email @Size(max = 120) String email,
        Boolean tourEnabled
) {
}
