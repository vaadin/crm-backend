package com.vaadin.example.crm.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@SuppressWarnings("serial")
public class Note extends AbstractEntity {

    @NotBlank
    @Size(min = 4, max = 1024)
    private String content;

    @NotNull
    private Date created;

    @OneToOne
    @NotNull
    private User createdBy;

    public Note() {
    }

    public Note(String content, Date created, User createdBy) {
        super();
        this.content = content;
        this.created = created;
        this.createdBy = createdBy;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

}
