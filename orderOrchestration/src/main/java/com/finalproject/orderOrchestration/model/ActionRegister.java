package com.finalproject.orderOrchestration.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class ActionRegister {
    @Id
    String id;
    String actionName;
    
    public ActionRegister() {
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getActionName() {
        return actionName;
    }
    public void setActionName(String actionName) {
        this.actionName = actionName;
    }
}
