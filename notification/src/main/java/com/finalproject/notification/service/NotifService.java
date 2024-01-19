package com.finalproject.notification.service;

import org.springframework.stereotype.Service;

import com.finalproject.notification.model.Notification;
import com.finalproject.notification.model.OrderAPI;

import reactor.core.publisher.Mono;

@Service
public interface NotifService {
    Mono<Notification> createNotification(String queue, OrderAPI notif);
    Mono<Notification> deleteReal(String queue, OrderAPI notif);
}
