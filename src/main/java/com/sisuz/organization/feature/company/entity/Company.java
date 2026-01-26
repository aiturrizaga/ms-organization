package com.sisuz.organization.feature.company.entity;

import com.sisuz.organization.common.persistence.AuditingEntity;
import com.sisuz.organization.common.persistence.IdentityDocumentType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "company")
public class Company extends AuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Size(max = 120)
    @NotNull
    @Column(name = "trade_name", nullable = false, length = 120)
    private String tradeName;

    @Size(max = 120)
    @NotNull
    @Column(name = "legal_name", nullable = false, length = 120)
    private String legalName;

    @Size(max = 120)
    @NotNull
    @Column(name = "legal_owner", nullable = false, length = 120)
    private String legalOwner;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "document_type_id")
    private IdentityDocumentType documentType;

    @Size(max = 60)
    @Column(name = "document_number", length = 60)
    private String documentNumber;

    @NotNull
    @Column(name = "billing_address_id", nullable = false)
    private Integer billingAddressId;

    @NotNull
    @Column(name = "legal_address_id", nullable = false)
    private Integer legalAddressId;

    @Column(name = "logo_file_id")
    private Long logoFileId;

    @NotNull
    @Column(name = "state", nullable = false)
    private Integer state;

    @NotNull
    @ColumnDefault("true")
    @Column(name = "is_active", nullable = false)
    private boolean active = true;

}