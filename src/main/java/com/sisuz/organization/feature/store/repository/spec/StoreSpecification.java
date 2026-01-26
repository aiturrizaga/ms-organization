package com.sisuz.organization.feature.store.repository.spec;

import com.sisuz.organization.feature.store.entity.Store;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public final class StoreSpecification {
    private StoreSpecification() {}

    public static Specification<Store> withFilters(
            Boolean active,
            UUID companyId,
            String name,
            String email,
            String phone,
            Integer state,
            LocalDate createdDate
    ) {
        return Specification.allOf(
                activeEq(active),
                companyEq(companyId),
                nameLike(name),
                emailLike(email),
                phoneLike(phone),
                stateEq(state),
                createdAtOnDate(createdDate)
        );
    }

    public static Specification<Store> activeEq(Boolean active) {
        return (root, query, cb) -> active == null ? cb.conjunction() : cb.equal(root.get("active"), active);
    }

    public static Specification<Store> companyEq(UUID companyId) {
        return (root, query, cb) -> {
            if (companyId == null) return cb.conjunction();
            return cb.equal(root.get("company").get("id"), companyId);
        };
    }

    public static Specification<Store> stateEq(Integer state) {
        return (root, query, cb) -> state == null ? cb.conjunction() : cb.equal(root.get("state"), state);
    }

    public static Specification<Store> nameLike(String value) {
        return (root, query, cb) -> {
            if (value == null || value.isBlank()) return cb.conjunction();
            return cb.like(cb.lower(root.get("name")), "%" + value.trim().toLowerCase() + "%");
        };
    }

    public static Specification<Store> emailLike(String value) {
        return (root, query, cb) -> {
            if (value == null || value.isBlank()) return cb.conjunction();
            return cb.like(cb.lower(root.get("email")), "%" + value.trim().toLowerCase() + "%");
        };
    }

    public static Specification<Store> phoneLike(String value) {
        return (root, query, cb) -> {
            if (value == null || value.isBlank()) return cb.conjunction();
            return cb.like(cb.lower(root.get("phone")), "%" + value.trim().toLowerCase() + "%");
        };
    }

    public static Specification<Store> createdAtOnDate(LocalDate date) {
        return (root, query, cb) -> {
            if (date == null) return cb.conjunction();
            LocalDateTime start = date.atStartOfDay();
            LocalDateTime end = date.plusDays(1).atStartOfDay();
            return cb.between(root.get("createdAt"), start, end);
        };
    }
}
