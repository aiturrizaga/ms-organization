package com.sisuz.organization.model.response;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class UserSessionResponse {

    private final TenantDto tenant;
    private final CompanyDto company;
    private final UserDto user;

    @Getter
    @Builder
    public static class TenantDto {
        private final UUID id;
        private final String name;
    }

    @Getter
    @Builder
    public static class CompanyDto {
        private final UUID id;
        private final String name;
    }

    @Getter
    @Builder
    public static class UserDto {
        private final UUID id;
        private final String email;
        private final long partnerId;
        private final boolean active;
    }
}
