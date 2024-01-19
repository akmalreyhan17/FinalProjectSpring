package com.finalproject.orderAPI.model;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;

public class OrderAPI {
    @NotBlank(message = "id mandatory")
    String id;
    
    String productName;
    Long quantity;
    String status;
    LocalDateTime createdDate;
    LocalDateTime updatedDate;
    String orderType;
    String crudType;
    Boolean newOrder = true;
    
    public OrderAPI() {
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
    public String getCrudType() {
        return crudType;
    }
    public void setCrudType(String crudType) {
        this.crudType = crudType;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Boolean getNewOrder() {
        return newOrder;
    }

    public void setNewOrder(Boolean newOrder) {
        this.newOrder = newOrder;
    }
}
