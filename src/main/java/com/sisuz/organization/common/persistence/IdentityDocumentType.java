package com.sisuz.organization.common.persistence;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "identity_document_type")
public class IdentityDocumentType extends AuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 120)
    @NotNull
    @Column(name = "name", nullable = false, length = 120)
    private String name;

    @Size(max = 10)
    @NotNull
    @Column(name = "abbr", nullable = false, length = 10)
    private String abbr;

    @Size(max = 255)
    @Column(name = "description")
    private String description;

    @Size(max = 60)
    @Column(name = "regex", length = 60)
    private String regex;

    @Column(name = "max_size")
    private Integer maxSize;

    @NotNull
    @Column(name = "is_active", nullable = false)
    private boolean active = true;


}