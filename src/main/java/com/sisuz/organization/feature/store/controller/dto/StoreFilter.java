package com.sisuz.organization.feature.store.controller.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record StoreFilter(
        Boolean active,
        String name,
        String email,
        String phone,
        Integer state,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate createdDate
) {
}
