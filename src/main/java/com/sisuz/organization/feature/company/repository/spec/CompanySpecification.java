package com.sisuz.organization.feature.company.repository.spec;

import com.sisuz.organization.feature.company.entity.Company;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalDateTime;

public final class CompanySpecification {

    private CompanySpecification() {}

    public static Specification<Company> withFilters(
            Boolean active,
            String name,
            String documentNumber,
            Integer state,
            LocalDate createdDate
    ) {
        return Specification.allOf(
                activeEq(active),
                nameLike(name),
                documentNumberLike(documentNumber),
                stateEq(state),
                createdAtOnDate(createdDate)
        );
    }

    public static Specification<Company> activeEq(Boolean active) {
        return (root, query, cb) ->
                active == null ? cb.conjunction() : cb.equal(root.get("active"), active);
    }

    public static Specification<Company> stateEq(Integer state) {
        return (root, query, cb) ->
                state == null ? cb.conjunction() : cb.equal(root.get("state"), state);
    }

    public static Specification<Company> nameLike(String name) {
        if (name == null || name.isBlank()) {
            return (root, query, cb) -> cb.conjunction();
        }
        return Specification.anyOf(
                tradeNameLike(name),
                legalNameLike(name)
        );
    }

    public static Specification<Company> tradeNameLike(String value) {
        return (root, query, cb) -> {
            if (value == null || value.isBlank()) return cb.conjunction();
            return cb.like(cb.lower(root.get("tradeName")),
                    "%" + value.trim().toLowerCase() + "%");
        };
    }

    public static Specification<Company> legalNameLike(String value) {
        return (root, query, cb) -> {
            if (value == null || value.isBlank()) return cb.conjunction();
            return cb.like(cb.lower(root.get("legalName")),
                    "%" + value.trim().toLowerCase() + "%");
        };
    }

    public static Specification<Company> documentNumberLike(String value) {
        return (root, query, cb) -> {
            if (value == null || value.isBlank()) return cb.conjunction();
            return cb.like(cb.lower(root.get("documentNumber")),
                    "%" + value.trim().toLowerCase() + "%");
        };
    }

    public static Specification<Company> createdAtOnDate(LocalDate date) {
        return (root, query, cb) -> {
            if (date == null) return cb.conjunction();
            LocalDateTime start = date.atStartOfDay();
            LocalDateTime end = date.plusDays(1).atStartOfDay();
            return cb.between(root.get("createdAt"), start, end);
        };
    }
}
