package com.dsniatecki.yourfleetmanager.repositories;


import com.dsniatecki.yourfleetmanager.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
