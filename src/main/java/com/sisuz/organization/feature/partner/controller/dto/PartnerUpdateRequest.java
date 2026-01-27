package com.sisuz.organization.feature.partner.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record PartnerUpdateRequest(
        @Size(max = 20) String type,
        @Size(max = 255) String displayName,
        Integer documentTypeId,
        @Size(max = 50) String documentNumber,
        @Size(max = 120) String firstName,
        @Size(max = 120) String lastName,
        @Size(max = 255) String legalName,
        @Size(max = 120) String tradeName,
        @Size(max = 20) String phone,
        @Size(max = 20) String mobile,
        @Email @Size(max = 120) String email,
        LocalDate importantDate
) {}
