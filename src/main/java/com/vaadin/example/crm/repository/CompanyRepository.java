package com.vaadin.example.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vaadin.example.crm.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    List<Company> findByNameIgnoreCase(String name);
}
