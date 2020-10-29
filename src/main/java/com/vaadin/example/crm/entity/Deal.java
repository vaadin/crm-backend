package com.vaadin.example.crm.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@SuppressWarnings("serial")
public class Deal extends AbstractEntity {

    @NotBlank
    @Size(min = 4, max = 255)
    private String name;

    @NotNull
    @Min(0)
    private Double amount;

    @NotNull
    @OneToOne
    private DealState state;

    @Column
    private Date closed;

    @OneToMany
    @Cascade(CascadeType.ALL)
    @OrderBy("created DESC")
    private List<Note> notes;

    @ManyToOne
    @NotNull
    private Contact contact;

    @ManyToOne
    private Company company;

    @ManyToOne
    @NotNull
    private User dealOwner;

    public Deal() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public DealState getState() {
        return state;
    }

    public void setState(DealState state) {
        this.state = state;
    }

    public Date getClosed() {
        return closed;
    }

    public void setClosed(Date closed) {
        this.closed = closed;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public User getDealOwner() {
        return dealOwner;
    }

    public void setDealOwner(User dealOwner) {
        this.dealOwner = dealOwner;
    }
}
