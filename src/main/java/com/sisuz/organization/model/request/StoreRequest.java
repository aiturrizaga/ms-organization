package com.sisuz.organization.model.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record StoreRequest(
        @NotNull
        UUID companyId,

        @NotBlank
        @Size(max = 50)
        String name,

        @NotNull
        Integer addressId,

        @Size(max = 20)
        String phone,

        @Email
        @Size(max = 255)
        String email,

        @NotNull
        Integer state,

        Boolean isActive
) {}
