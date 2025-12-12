package com.sisuz.organization.service.impl;

import com.sisuz.organization.entity.Company;
import com.sisuz.organization.exception.NotFoundException;
import com.sisuz.organization.model.mapper.CompanyMapper;
import com.sisuz.organization.model.request.CompanyRequest;
import com.sisuz.organization.model.response.CompanyResponse;
import com.sisuz.organization.repository.CompanyRepository;
import com.sisuz.organization.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    @Override
    public CompanyResponse create(CompanyRequest request) {
        Company company = companyMapper.toEntity(request);
        return companyMapper.toDto(companyRepository.save(company));
    }

    @Override
    public Page<CompanyResponse> getAll(Pageable pageable) {
        return companyRepository.findAll(pageable)
                .map(companyMapper::toDto);
    }

    @Override
    public CompanyResponse getById(UUID companyId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new NotFoundException("Company not found:" + companyId));
        return companyMapper.toDto(company);
    }

    @Override
    public CompanyResponse update(UUID companyId, CompanyRequest request) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new NotFoundException("Company not found:" + companyId));

        companyMapper.updateEntity(request, company);
        return companyMapper.toDto(companyRepository.save(company));
    }
}
