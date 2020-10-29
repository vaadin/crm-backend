package com.vaadin.example.crm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

@SuppressWarnings("serial")
@Entity
public class DealState extends AbstractEntity {

    public DealState() {
    }

    public DealState(String name, boolean active, int order) {
        this.name = name;
        this.active = active;
        orderNum = order;
    }

    @NotBlank
    @Size(min = 3, max = 255)
    private String name;

    private boolean active;

    @NotNull
    @Column(unique = true)
    private int orderNum;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

}
