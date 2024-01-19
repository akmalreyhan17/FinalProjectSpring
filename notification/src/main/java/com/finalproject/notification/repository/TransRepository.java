package com.finalproject.notification.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.finalproject.notification.model.Trans;

@Repository
public interface TransRepository extends R2dbcRepository<Trans, Integer> {
    
}
