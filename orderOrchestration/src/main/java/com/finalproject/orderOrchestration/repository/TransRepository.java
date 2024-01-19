package com.finalproject.orderOrchestration.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.finalproject.orderOrchestration.model.Trans;

import reactor.core.publisher.Mono;

@Repository
public interface TransRepository extends R2dbcRepository<Trans, Integer> {
    @Query("select status from trans where order_id=:orderId order by id DESC limit 1")
    Mono<String> getStatus(@Param("orderId") String orderId);
}
