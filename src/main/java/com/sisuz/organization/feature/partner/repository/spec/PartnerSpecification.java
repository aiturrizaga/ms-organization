package com.sisuz.organization.feature.partner.repository.spec;

import com.sisuz.organization.feature.partner.entity.Partner;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public final class PartnerSpecification {

    private PartnerSpecification() {
    }

    public static Specification<Partner> withFilters(
            UUID tenantId,
            UUID companyId,
            Boolean active,
            String type,
            String name,
            String documentNumber,
            String email,
            LocalDate importantDate,
            LocalDate createdDate
    ) {
        return Specification.allOf(
                tenantEq(tenantId),
                companyEq(companyId),
                activeEq(active),
                typeEq(type),
                nameLike(name),
                documentNumberLike(documentNumber),
                emailLike(email),
                importantDateEq(importantDate),
                createdAtOnDate(createdDate)
        );
    }

    public static Specification<Partner> tenantEq(UUID tenantId) {
        return (root, query, cb) -> {
            if (tenantId == null) return cb.conjunction();
            return cb.equal(root.get("tenant").get("id"), tenantId);
        };
    }

    public static Specification<Partner> companyEq(UUID companyId) {
        return (root, query, cb) -> {
            if (companyId == null) return cb.conjunction();
            return cb.equal(root.get("company").get("id"), companyId);
        };
    }

    public static Specification<Partner> activeEq(Boolean active) {
        return (root, query, cb) -> active == null ? cb.conjunction() : cb.equal(root.get("active"), active);
    }

    public static Specification<Partner> typeEq(String type) {
        return (root, query, cb) -> {
            if (type == null || type.isBlank()) return cb.conjunction();
            return cb.equal(cb.lower(root.get("type")), type.trim().toLowerCase());
        };
    }

    public static Specification<Partner> nameLike(String value) {
        return (root, query, cb) -> {
            if (value == null || value.isBlank()) return cb.conjunction();

            String pattern = "%" + value.trim().toLowerCase() + "%";
            return cb.or(
                    cb.like(cb.lower(root.get("displayName")), pattern),
                    cb.like(cb.lower(root.get("firstName")), pattern),
                    cb.like(cb.lower(root.get("lastName")), pattern),
                    cb.like(cb.lower(root.get("legalName")), pattern),
                    cb.like(cb.lower(root.get("tradeName")), pattern)
            );
        };
    }

    public static Specification<Partner> documentNumberLike(String value) {
        return (root, query, cb) -> {
            if (value == null || value.isBlank()) return cb.conjunction();
            return cb.like(cb.lower(root.get("documentNumber")), "%" + value.trim().toLowerCase() + "%");
        };
    }

    public static Specification<Partner> emailLike(String value) {
        return (root, query, cb) -> {
            if (value == null || value.isBlank()) return cb.conjunction();
            return cb.like(cb.lower(root.get("email")), "%" + value.trim().toLowerCase() + "%");
        };
    }

    public static Specification<Partner> importantDateEq(LocalDate date) {
        return (root, query, cb) -> date == null ? cb.conjunction() : cb.equal(root.get("importantDate"), date);
    }

    public static Specification<Partner> createdAtOnDate(LocalDate date) {
        return (root, query, cb) -> {
            if (date == null) return cb.conjunction();
            LocalDateTime start = date.atStartOfDay();
            LocalDateTime end = date.plusDays(1).atStartOfDay();
            return cb.between(root.get("createdAt"), start, end);
        };
    }
}
