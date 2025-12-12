package com.sisuz.organization.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CompanyRequest(

        @NotBlank
        @Size(max = 100)
        String legalName,

        @NotBlank
        @Size(max = 100)
        String tradeName,

        @NotBlank
        @Size(min = 11, max = 11, message = "RUC must be 11 characters")
        String ruc,

        Long logoFileId,

        @NotBlank
        @Size(max = 100)
        String legalOwner,

        @NotNull
        Integer legalAddressId,

        @NotNull
        Integer billingAddressId,

        @NotNull
        Integer state,

        Boolean isActive
) {}
