package com.finalproject.crm.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.finalproject.crm.model.Crm;

import reactor.core.publisher.Mono;

@Repository
public interface CrmRepository extends R2dbcRepository<Crm, String> {
    @Query("DELETE from crm where id=:id")
    Mono<Crm> deletenew(@Param("id") String id);
}
