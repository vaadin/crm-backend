package com.vaadin.example.crm.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@SuppressWarnings("serial")
public class Company extends AbstractEntity {

    @NotBlank
    @Size(max = 255)
    private String name;

    @Size(max = 1024)
    private String description;

    @Enumerated(EnumType.STRING)
    private Country country;

    @Size(max = 127)
    private String state;

    @OneToMany(mappedBy = "company")
    @Cascade(CascadeType.DELETE)
    private Collection<Contact> contacts;

    @OneToMany(mappedBy = "company")
    @Cascade(CascadeType.DELETE)
    private Collection<Deal> deals;

    @ManyToOne
    private User accountManager;

    public Company() {

    }

    public Company(@NotBlank @Size(max = 255) String name, @Size(max = 1024) String description, Country country, @Size(max = 127) String state,
            User accountManager) {

        this.name = name;
        this.description = description;
        this.country = country;
        this.state = state;
        this.accountManager = accountManager;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Collection<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Collection<Contact> contacts) {
        this.contacts = contacts;
    }

    public Collection<Deal> getDeals() {
        return deals;
    }

    public void setDeals(Collection<Deal> deals) {
        this.deals = deals;
    }

    public User getAccountManager() {
        return accountManager;
    }

    public void setAccountManager(User accountManager) {
        this.accountManager = accountManager;
    }
}
