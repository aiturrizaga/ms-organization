package com.sisuz.organization.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CreatePartnerRequest(
        @NotNull
        @Pattern(regexp = "^(PERSON|COMPANY)$", message = "partnerType must be PERSON or COMPANY")
        @Size(max = 20)
        String partnerType,

        @Size(max = 120) String firstName,
        @Size(max = 120) String lastName,

        @Size(max = 255) String displayName,
        @Size(max = 255) String legalName,
        @Size(max = 120) String tradeName,

        Integer documentTypeId,
        String documentNumber,

        @Email String email,
        @Size(max = 20) String phone,
        @Size(max = 20) String mobile,

        LocalDate importantDate
) {
}
