package com.vaadin.example.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vaadin.example.crm.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmailIgnoreCase(String email);
}
