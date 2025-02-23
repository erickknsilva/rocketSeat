package com.erickWck.modules.company.repository;

import com.erickWck.modules.company.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CompanyRepository
        extends JpaRepository<CompanyEntity, UUID> {

    Optional<CompanyEntity> findByUsernameOrEmail(String email, String username);

    Optional<CompanyEntity> findByUsername(String username);

}