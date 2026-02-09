package com.sisuz.organization.feature.user.service;

import com.sisuz.organization.common.exception.BusinessException;
import com.sisuz.organization.common.exception.NotFoundException;
import com.sisuz.organization.feature.company.entity.Company;
import com.sisuz.organization.feature.company.repository.CompanyRepository;
import com.sisuz.organization.feature.partner.repository.PartnerRepository;
import com.sisuz.organization.feature.tenant.entity.Tenant;
import com.sisuz.organization.feature.tenant.repository.TenantRepository;
import com.sisuz.organization.feature.user.controller.dto.*;
import com.sisuz.organization.feature.user.entity.User;
import com.sisuz.organization.feature.user.entity.UserCompany;
import com.sisuz.organization.feature.user.mapper.UserMapper;
import com.sisuz.organization.feature.user.repository.UserCompanyRepository;
import com.sisuz.organization.feature.user.repository.UserRepository;
import com.sisuz.organization.feature.user.repository.spec.UserSpecification;
import com.sisuz.organization.security.context.ContextAwareService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ContextAwareService implements UserService {

    private final UserRepository userRepository;
    private final UserCompanyRepository userCompanyRepository;

    private final PartnerRepository partnerRepository;
    private final TenantRepository tenantRepository;
    private final CompanyRepository companyRepository;

    private final UserMapper mapper;

    private UserResponse enrich(User user) {
        List<UserCompany> companies = userCompanyRepository.findAllByAppUserId(user.getId());
        List<UserCompanyResponse> companyDtos = mapper.toCompanyResponseList(companies);
        UserResponse base = mapper.toResponse(user);
        return new UserResponse(
                base.id(),
                base.partnerId(),
                base.email(),
                base.tourEnabled(),
                base.active(),
                companyDtos
        );
    }

    @Override
    public UserResponse create(UserCreateRequest request) {
        if (userRepository.existsByEmailIgnoreCase(request.email())) {
            throw new BusinessException(5102, "Email already exists");
        }

        if (!partnerRepository.existsById(request.partnerId())) {
            throw new BusinessException(5103, "Partner not found with id: " + request.partnerId());
        }

        User entity = mapper.toEntity(request);
        entity.setActive(true);
        User saved = userRepository.save(entity);
        return enrich(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getById(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> NotFoundException.of("User", id));
        return enrich(user);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserResponse> getAll(Pageable pageable, UserFilter filter) {
        Specification<User> spec = UserSpecification.withFilters(
                filter.active(),
                filter.partnerId(),
                filter.email(),
                filter.createdDate()
        );

        return userRepository.findAll(spec, pageable)
                .map(this::enrich);
    }

    @Override
    public UserSessionResponse getSession(String userId) {
        List<UserCompany> userCompany = userCompanyRepository.findAllByAppUserId(UUID.fromString(userId));

        var company = userCompany.getFirst().getCompany();
        var tenant = userCompany.getFirst().getTenant();
        var user = userCompany.getFirst().getAppUser();

        return UserSessionResponse.builder()
                .tenant(
                        UserSessionResponse.TenantDto.builder()
                                .id(tenant.getId())
                                .name(tenant.getName())
                                .build()
                )
                .company(
                        UserSessionResponse.CompanyDto.builder()
                                .id(company.getId())
                                .name(company.getLegalName())
                                .build()
                )
                .user(
                        UserSessionResponse.UserDto.builder()
                                .id(user.getId())
                                .email(user.getEmail())
                                .partnerId(user.getPartner().getId())
                                .active(user.isActive())
                                .build()
                )
                .build();
    }

    @Override
    public UserResponse update(UUID id, UserUpdateRequest request) {
        User user = userRepository.findById(id).orElseThrow(() -> NotFoundException.of("User", id));
        mapper.updateEntity(user, request);
        User saved = userRepository.save(user);
        return enrich(saved);
    }

    @Override
    public void deactivate(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> NotFoundException.of("User", id));
        if (!user.isActive()) throw new BusinessException(5104, "User is already inactive");
        user.setActive(false);
        userRepository.save(user);
    }

    @Override
    public void activate(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> NotFoundException.of("User", id));
        if (user.isActive()) throw new BusinessException(5105, "User is already active");
        user.setActive(true);
        userRepository.save(user);
    }

    @Override
    public void assignCompany(UUID userId) {
        UUID tenantId = currentTenantId();
        UUID companyId = currentCompanyId();

        User user = userRepository.findById(userId).orElseThrow(() -> NotFoundException.of("User", userId));

        UserCompany relation = userCompanyRepository
                .findByAppUserIdAndTenantIdAndCompanyId(userId, tenantId, companyId)
                .orElseGet(() -> {
                    UserCompany uc = new UserCompany();
                    uc.setAppUser(user);

                    Tenant t = new Tenant();
                    t.setId(tenantId);
                    uc.setTenant(t);

                    Company c = new Company();
                    c.setId(companyId);
                    uc.setCompany(c);

                    uc.setActive(true);
                    return uc;
                });

        relation.setActive(true);
        userCompanyRepository.save(relation);
    }

    @Override
    public void unassignCompany(UUID userId) {
        UUID tenantId = currentTenantId();
        UUID companyId = currentCompanyId();

        UserCompany relation = userCompanyRepository
                .findByAppUserIdAndTenantIdAndCompanyId(userId, tenantId, companyId)
                .orElseThrow(() -> new NotFoundException("UserCompany relation not found"));

        if (!relation.isActive()) {
            throw new BusinessException(5108, "UserCompany relation is already inactive");
        }

        relation.setActive(false);
        userCompanyRepository.save(relation);
    }
}
