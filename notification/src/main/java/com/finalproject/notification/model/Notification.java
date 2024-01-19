package com.finalproject.notification.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table
public class Notification implements Persistable<String> {
    @Id
    String id;
    String content;

    @Transient
    private Boolean newNotif = false;
    
    public Notification() {
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    @Transient
    public boolean isNew() {
        return this.newNotif || id == null;
    }

    public Notification setAsNew() {
        this.newNotif = true;
        return this;
    }
}
