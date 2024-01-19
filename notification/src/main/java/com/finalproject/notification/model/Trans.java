package com.finalproject.notification.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table
public class Trans implements Persistable<Integer> {
    @Id
    Integer id;
    String orderId;
    String queueName;
    String status;

    @Transient
    private Boolean newTrans = false;

    public Trans() {
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public String getQueueName() {
        return queueName;
    }
    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    @Transient
    public boolean isNew() {
        return this.newTrans || id == null;
    }

    public Trans setAsNew() {
        this.newTrans = true;
        return this;
    }
}

