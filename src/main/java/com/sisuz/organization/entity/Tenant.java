package com.sisuz.organization.entity;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tenant")
@Builder
public class Tenant extends AuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "domain", length = 255)
    private String domain;

    @Column(name = "subdomain", length = 255)
    private String subdomain;

    @Column(name = "slug", length = 50)
    private String slug;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "settings", columnDefinition = "jsonb")
    private JsonNode settings;

    @Column(name = "plan_id", nullable = false)
    private Integer planId;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

}
