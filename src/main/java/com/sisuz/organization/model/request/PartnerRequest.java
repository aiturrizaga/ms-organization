package com.sisuz.organization.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record PartnerRequest(

        @NotNull
        UUID tenantId,

        @NotNull
        UUID companyId,

        @Size(max = 20)
        String code,

        @Size(max = 255)
        String name,

        @Size(max = 255)
        String lastname,

        @Size(max = 255)
        String legalName,

        @NotNull
        Integer documentTypeId,

        @NotBlank
        @Size(max = 50)
        String documentNumber,

        @Email
        @Size(max = 50)
        String email,

        @Size(max = 20)
        String phone,

        @Size(max = 20)
        String mobile,

        @Size(max = 255)
        String address,

        @Size(max = 255)
        String addressReference,

        @Size(max = 6)
        String ubigeoId,

        Boolean isCompany,
        Boolean isActive
) {}
