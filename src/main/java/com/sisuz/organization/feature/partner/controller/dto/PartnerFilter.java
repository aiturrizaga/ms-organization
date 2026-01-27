package com.sisuz.organization.feature.partner.controller.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record PartnerFilter(
        Boolean active,
        String type,
        String name,
        String documentNumber,
        String email,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate importantDate,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate createdDate
) {}
