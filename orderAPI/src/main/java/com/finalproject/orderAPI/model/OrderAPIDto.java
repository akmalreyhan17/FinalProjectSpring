package com.finalproject.orderAPI.model;

import jakarta.validation.constraints.NotBlank;

public class OrderAPIDto {
    @NotBlank(message = "id mandatory")
    String id;
    
    String productName;
    Long quantity;
    String orderType;
    
    public OrderAPIDto() {
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
    public String getOrderType() {
        return orderType;
    }
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
}
