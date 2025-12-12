package com.sisuz.organization.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "partner")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Partner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Column(name = "code", length = 20)
    private String code;

    @Column(name = "name", length = 255)
    private String name;

    @Column(name = "lastname", length = 255)
    private String lastname;

    @Column(name = "legal_name", length = 255)
    private String legalName;

    @Column(name = "document_type_id", nullable = false)
    private Integer documentTypeId;

    @Column(name = "document_number", length = 50, nullable = false)
    private String documentNumber;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "mobile", length = 20)
    private String mobile;

    @Column(name = "address", length = 255)
    private String address;

    @Column(name = "address_reference", length = 255)
    private String addressReference;

    @Column(name = "ubigeo_id", length = 6)
    private String ubigeoId;

    @Column(name = "is_company", nullable = false)
    private Boolean isCompany = false;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

}
