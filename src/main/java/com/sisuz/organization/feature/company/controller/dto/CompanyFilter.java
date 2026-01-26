package com.sisuz.organization.feature.company.controller.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record CompanyFilter(
        Boolean active,
        String name,
        String documentNumber,
        Integer state,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate createdDate
) {
}
