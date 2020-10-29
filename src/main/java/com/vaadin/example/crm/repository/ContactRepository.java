package com.vaadin.example.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vaadin.example.crm.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    List<Contact> findByNameIgnoreCase(String name);
}
