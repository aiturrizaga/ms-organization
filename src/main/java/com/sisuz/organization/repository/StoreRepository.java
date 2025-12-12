package com.sisuz.organization.repository;

import com.sisuz.organization.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StoreRepository extends JpaRepository<Store, UUID> {
    Page<Store> findByCompanyId(UUID companyId, Pageable pageable);
}
