package com.sisuz.organization.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "store")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Store extends AuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "address_id", nullable = false)
    private Integer addressId;

    @Column(name = "phone", length = 20)
    private String phone;

    @Email
    @Column(name = "email", length = 255)
    private String email;

    @Column(name = "state", nullable = false)
    private Integer state;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;
}
