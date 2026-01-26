package com.sisuz.organization.feature.tenant.entity;

import com.sisuz.organization.common.persistence.AuditingEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "tenant")
public class Tenant extends AuditingEntity {
    @Id
    @ColumnDefault("gen_random_uuid()")
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Size(max = 255)
    @Column(name = "domain")
    private String domain;

    @Size(max = 60)
    @NotNull
    @Column(name = "name", nullable = false, length = 60)
    private String name;

    @NotNull
    @Column(name = "plan_id", nullable = false)
    private Integer planId;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "settings", columnDefinition = "jsonb")
    private Map<String, Object> settings;

    @Size(max = 60)
    @Column(name = "slug", length = 60)
    private String slug;

    @Size(max = 255)
    @Column(name = "subdomain")
    private String subdomain;

    @NotNull
    @Column(name = "is_active", nullable = false)
    private boolean active = true;

}