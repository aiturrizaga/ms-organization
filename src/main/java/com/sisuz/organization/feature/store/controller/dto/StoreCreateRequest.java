package com.sisuz.organization.feature.store.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record StoreCreateRequest(
        @NotBlank @Size(max = 60) String name,
        @NotNull Integer addressId,
        @Email @Size(max = 120) String email,
        @Size(max = 20) String phone,
        @NotNull Integer state
) {
}
