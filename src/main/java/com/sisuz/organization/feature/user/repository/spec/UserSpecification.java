package com.sisuz.organization.feature.user.repository.spec;

import com.sisuz.organization.feature.user.entity.User;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalDateTime;

public final class UserSpecification {

    private UserSpecification() {
    }

    public static Specification<User> withFilters(
            Boolean active,
            Long partnerId,
            String email,
            LocalDate createdDate
    ) {
        return Specification.allOf(
                activeEq(active),
                partnerEq(partnerId),
                emailLike(email),
                createdAtOnDate(createdDate)
        );
    }

    public static Specification<User> activeEq(Boolean active) {
        return (root, query, cb) -> active == null ? cb.conjunction() : cb.equal(root.get("active"), active);
    }

    public static Specification<User> partnerEq(Long partnerId) {
        return (root, query, cb) -> {
            if (partnerId == null) return cb.conjunction();
            return cb.equal(root.get("partner").get("id"), partnerId);
        };
    }

    public static Specification<User> emailLike(String value) {
        return (root, query, cb) -> {
            if (value == null || value.isBlank()) return cb.conjunction();
            return cb.like(cb.lower(root.get("email")), "%" + value.trim().toLowerCase() + "%");
        };
    }

    public static Specification<User> createdAtOnDate(LocalDate date) {
        return (root, query, cb) -> {
            if (date == null) return cb.conjunction();
            LocalDateTime start = date.atStartOfDay();
            LocalDateTime end = date.plusDays(1).atStartOfDay();
            return cb.between(root.get("createdAt"), start, end);
        };
    }
}
