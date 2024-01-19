package com.finalproject.orderAPI.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.finalproject.orderAPI.model.Crm;

@Repository
public interface CrmRepository extends R2dbcRepository<Crm, String> {
    
}
