package com.sisuz.organization.feature.user.entity;

import com.sisuz.organization.common.persistence.AuditingEntity;
import com.sisuz.organization.feature.partner.entity.Partner;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "app_user")
public class User extends AuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "partner_id", nullable = false)
    private Partner partner;

    @Size(max = 120)
    @NotNull
    @Column(name = "email", nullable = false, length = 120)
    private String email;

    @NotNull
    @ColumnDefault("true")
    @Column(name = "tour_enabled", nullable = false)
    private Boolean tourEnabled;

    @NotNull
    @ColumnDefault("true")
    @Column(name = "is_active", nullable = false)
    private boolean active = true;

}