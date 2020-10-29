package com.vaadin.example.crm.entity;

import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@SuppressWarnings("serial")
public class Contact extends AbstractEntity {

    @NotBlank
    @Size(max = 255)
    private String name;

    @Email
    @NotBlank
    @Size(max = 255)
    @Column(unique = true)
    private String email;

    @ManyToOne
    private Company company;

    @ManyToMany(mappedBy = "contact")
    private Collection<Deal> deals;

    @OneToMany
    @Cascade(CascadeType.ALL)
    @OrderBy("created DESC")
    private List<Note> notes;

    public Contact() {
    }

    public Contact(@NotBlank @Size(max = 255) String name, @Email @NotBlank @Size(max = 255) String email, Company company) {
        super();
        this.name = name;
        this.email = email;
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Collection<Deal> getDeals() {
        return deals;
    }

    public void setDeals(Collection<Deal> deals) {
        this.deals = deals;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }
}
