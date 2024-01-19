package com.finalproject.crm.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table
public class Crm implements Persistable<String> {
    @Id
    String id;
    String productName;
    Long quantity;
    String status;
    LocalDateTime createdDate;
    LocalDateTime updatedDate;

    @Transient
    private Boolean newCrm = false;

    public Crm() {
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public Long getQuantity() {
        return quantity;
    }
    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }
    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Override
    @Transient
    public boolean isNew() {
        return this.newCrm || id == null;
    }

    public Crm setAsNew() {
        this.newCrm = true;
        return this;
    }
}
