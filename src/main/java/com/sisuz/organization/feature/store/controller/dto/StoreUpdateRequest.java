package com.sisuz.organization.feature.store.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record StoreUpdateRequest(
        @Size(max = 60) String name,
        Integer addressId,
        @Email @Size(max = 120) String email,
        @Size(max = 20) String phone,
        Integer state
) {
}
