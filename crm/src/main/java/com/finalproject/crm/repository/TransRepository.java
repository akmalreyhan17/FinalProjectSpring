package com.finalproject.crm.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.finalproject.crm.model.Trans;

@Repository
public interface TransRepository extends R2dbcRepository<Trans, Integer> {
    
}
