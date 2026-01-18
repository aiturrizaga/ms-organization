package com.sisuz.organization.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record UserCreateRequest(
        @NotNull UUID id,
        @NotNull Integer partnerId,
        @NotNull @Email @Size(max = 120) String email,
        Boolean tourEnabled
) {
}
