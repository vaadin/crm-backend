package com.vaadin.example.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vaadin.example.crm.entity.Deal;

public interface DealRepository extends JpaRepository<Deal, Long> {

    List<Deal> findByNameIgnoreCase(String name);
}
