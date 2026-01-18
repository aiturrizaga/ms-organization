package com.sisuz.organization.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.Check;

import java.time.LocalDate;

@Entity
@Table(
        name = "partner",
        indexes = {
                @Index(
                        name = "idx_partner_tenant_company_type_active",
                        columnList = "tenant_id, company_id, partner_type, is_active"
                )
        }
)
@Check(constraints = "partner_type IN ('PERSON','COMPANY')")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Partner extends AuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @NotNull
    @Size(max = 20)
    @Pattern(regexp = "^(PERSON|COMPANY)$", message = "partnerType must be PERSON or COMPANY")
    @Column(name = "partner_type", nullable = false, length = 20)
    private String partnerType;

    @Size(max = 120)
    @Column(name = "first_name", length = 120)
    private String firstName;

    @Size(max = 120)
    @Column(name = "last_name", length = 120)
    private String lastName;

    @Size(max = 255)
    @Column(name = "display_name", length = 255)
    private String displayName;

    @Size(max = 255)
    @Column(name = "legal_name", length = 255)
    private String legalName;

    @Size(max = 120)
    @Column(name = "trade_name", length = 120)
    private String tradeName;

    @Column(name = "document_type_id")
    private Integer documentTypeId;

    @Size(max = 50)
    @Column(name = "document_number", length = 50)
    private String documentNumber;

    @Email
    @Size(max = 120)
    @Column(name = "email", length = 120)
    private String email;

    @Size(max = 20)
    @Column(name = "phone", length = 20)
    private String phone;

    @Size(max = 20)
    @Column(name = "mobile", length = 20)
    private String mobile;

    @Column(name = "important_date")
    private LocalDate importantDate;

    @Builder.Default
    @NotNull
    @Column(name = "is_active", nullable = false)
    private boolean active = true;

}
