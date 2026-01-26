package com.sisuz.organization.feature.company.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CompanyCreateRequest(
        @NotBlank @Size(max = 120) String tradeName,
        @NotBlank @Size(max = 120) String legalName,
        @NotBlank @Size(max = 120) String legalOwner,
        Integer documentTypeId,
        @Size(max = 60) String documentNumber,
        @NotNull Integer billingAddressId,
        @NotNull Integer legalAddressId,
        @NotNull Integer state
) {}
