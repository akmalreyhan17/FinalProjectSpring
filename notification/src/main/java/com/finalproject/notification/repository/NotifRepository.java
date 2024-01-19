package com.finalproject.notification.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.finalproject.notification.model.Notification;

import reactor.core.publisher.Mono;

@Repository
public interface NotifRepository extends R2dbcRepository<Notification, String> {
    @Query("UPDATE notification set content=:content where id=:id")
    Mono<Notification> updatenew(@Param("id") String id, @Param("content") String content);
}
