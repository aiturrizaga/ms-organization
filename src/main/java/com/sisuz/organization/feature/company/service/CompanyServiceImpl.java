package com.sisuz.organization.feature.company.service;

import com.sisuz.organization.common.exception.BusinessException;
import com.sisuz.organization.common.exception.NotFoundException;
import com.sisuz.organization.feature.company.controller.dto.CompanyCreateRequest;
import com.sisuz.organization.feature.company.controller.dto.CompanyFilter;
import com.sisuz.organization.feature.company.controller.dto.CompanyResponse;
import com.sisuz.organization.feature.company.controller.dto.CompanyUpdateRequest;
import com.sisuz.organization.feature.company.entity.Company;
import com.sisuz.organization.feature.company.mapper.CompanyMapper;
import com.sisuz.organization.feature.company.repository.CompanyRepository;
import com.sisuz.organization.feature.company.repository.spec.CompanySpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    @Override
    public CompanyResponse create(CompanyCreateRequest request) {
        Company entity = companyMapper.toEntity(request);
        entity.setActive(true);
        Company saved = companyRepository.save(entity);
        return companyMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public CompanyResponse getById(UUID id) {
        Company entity = companyRepository.findById(id).orElseThrow(() -> NotFoundException.of("Company", id));
        return companyMapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CompanyResponse> getAll(Pageable pageable, CompanyFilter filter) {
        Specification<Company> spec = CompanySpecification.withFilters(
                filter.active(),
                filter.name(),
                filter.documentNumber(),
                filter.state(),
                filter.createdDate()
        );
        return companyRepository.findAll(spec, pageable).map(companyMapper::toResponse);
    }

    @Override
    public CompanyResponse update(UUID id, CompanyUpdateRequest request) {
        Company entity = companyRepository.findById(id).orElseThrow(() -> NotFoundException.of("Company", id));
        companyMapper.updateEntity(entity, request);
        Company saved = companyRepository.save(entity);
        return companyMapper.toResponse(saved);
    }

    @Override
    public void deactivate(UUID id) {
        Company entity = companyRepository.findById(id).orElseThrow(() -> NotFoundException.of("Company", id));
        if (!entity.isActive()) throw new BusinessException(2101, "Company is already inactive");
        entity.setActive(false);
        companyRepository.save(entity);
    }

    @Override
    public void activate(UUID id) {
        Company entity = companyRepository.findById(id).orElseThrow(() -> NotFoundException.of("Company", id));
        if (entity.isActive()) throw new BusinessException(2102, "Company is already active");
        entity.setActive(true);
        companyRepository.save(entity);
    }
}
