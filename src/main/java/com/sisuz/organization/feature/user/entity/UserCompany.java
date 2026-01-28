package com.sisuz.organization.feature.user.entity;

import com.sisuz.organization.common.persistence.AuditingEntity;
import com.sisuz.organization.feature.company.entity.Company;
import com.sisuz.organization.feature.tenant.entity.Tenant;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "app_user_company")
public class UserCompany extends AuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "app_user_id", nullable = false)
    private User appUser;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @NotNull
    @ColumnDefault("true")
    @Column(name = "is_active", nullable = false)
    private boolean active = true;

}