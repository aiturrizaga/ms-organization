package com.sisuz.organization.model.response;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PartnerResponse {
    private Long id;
    private UUID tenantId;
    private UUID companyId;
    private String partnerType;
    private String firstName;
    private String lastName;
    private String displayName;
    private String legalName;
    private String tradeName;
    private Integer documentTypeId;
    private String documentNumber;
    private String email;
    private String phone;
    private String mobile;
    private LocalDate importantDate;
    private boolean active;
}
