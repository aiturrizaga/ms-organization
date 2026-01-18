package com.sisuz.organization.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@Entity
@Table(
        name = "app_user",
        indexes = {
                @Index(name = "idx_users_partner", columnList = "partner_id"),
                @Index(name = "idx_users_email", columnList = "email")
        }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends AuditingEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "partner_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "user_partner")
    )
    private Partner partner;

    @NotNull
    @Email
    @Size(max = 120)
    @Column(name = "email", nullable = false, length = 120)
    private String email;

    @NotNull
    @Column(name = "tour_enabled", nullable = false)
    private Boolean tourEnabled = true;

    @Builder.Default
    @Column(name = "is_active", nullable = false)
    private boolean active = true;
}
