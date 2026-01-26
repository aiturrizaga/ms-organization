package com.sisuz.organization.feature.partner;

import com.sisuz.organization.common.persistence.AuditingEntity;
import com.sisuz.organization.common.persistence.IdentityDocumentType;
import com.sisuz.organization.feature.company.entity.Company;
import com.sisuz.organization.feature.tenant.entity.Tenant;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "partner")
public class Partner extends AuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

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

    @Size(max = 20)
    @NotNull
    @Column(name = "type", nullable = false, length = 20)
    private String type;

    @Size(max = 255)
    @Column(name = "display_name")
    private String displayName;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "document_type_id")
    private IdentityDocumentType documentType;

    @Size(max = 50)
    @Column(name = "document_number", length = 50)
    private String documentNumber;

    @Size(max = 120)
    @Column(name = "first_name", length = 120)
    private String firstName;

    @Size(max = 120)
    @Column(name = "last_name", length = 120)
    private String lastName;

    @Size(max = 255)
    @Column(name = "legal_name")
    private String legalName;

    @Size(max = 120)
    @Column(name = "trade_name", length = 120)
    private String tradeName;

    @Size(max = 20)
    @Column(name = "phone", length = 20)
    private String phone;

    @Size(max = 20)
    @Column(name = "mobile", length = 20)
    private String mobile;

    @Size(max = 120)
    @Column(name = "email", length = 120)
    private String email;

    @Column(name = "important_date")
    private LocalDate importantDate;

    @NotNull
    @Column(name = "is_active", nullable = false)
    private boolean active = true;

}