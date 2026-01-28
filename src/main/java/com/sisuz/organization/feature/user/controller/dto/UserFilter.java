package com.sisuz.organization.feature.user.controller.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record UserFilter(
        Boolean active,
        Long partnerId,
        String email,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate createdDate
) {
}
