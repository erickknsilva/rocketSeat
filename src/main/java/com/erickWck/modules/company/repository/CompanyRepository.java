package com.erickWck.modules.company.repository;

import com.erickWck.modules.company.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CompanyRepository
        extends JpaRepository<Company, UUID> {

    Optional<Company> findByUsernameOrEmail(String email, String username);

    Optional<Company> findByUsername(String username);

}