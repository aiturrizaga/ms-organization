package com.sisuz.organization.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UserUpdateRequest(
        Integer partnerId,
        @Email @Size(max = 120) String email,
        Boolean tourEnabled,
        boolean active
) {
}
