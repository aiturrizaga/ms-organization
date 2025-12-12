package com.sisuz.organization.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "company")
@Builder
public class Company extends AuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "legal_name", length = 100, nullable = false)
    private String legalName;

    @Column(name = "trade_name", length = 100, nullable = false)
    private String tradeName;

    @Column(name = "ruc", length = 11, nullable = false)
    private String ruc;

    @Column(name = "logo_file_id")
    private Long logoFileId;

    @Column(name = "legal_owner", length = 100, nullable = false)
    private String legalOwner;

    @Column(name = "legal_address_id", nullable = false)
    private Integer legalAddressId;

    @Column(name = "billing_address_id", nullable = false)
    private Integer billingAddressId;

    @Column(name = "state", nullable = false)
    private Integer state;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private Set<Store> stores = new HashSet<>();
}
