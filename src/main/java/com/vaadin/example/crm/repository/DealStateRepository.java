package com.vaadin.example.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vaadin.example.crm.entity.DealState;

public interface DealStateRepository extends JpaRepository<DealState, Long> {

    DealState findByNameIgnoreCase(String name);
}
