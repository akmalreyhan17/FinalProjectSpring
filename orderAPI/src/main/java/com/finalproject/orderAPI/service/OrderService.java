package com.finalproject.orderAPI.service;

import org.springframework.stereotype.Service;

import com.finalproject.orderAPI.model.Crm;
import com.finalproject.orderAPI.model.OrderAPIDto;

import reactor.core.publisher.Mono;

@Service
public interface OrderService {
    Mono<OrderAPIDto> createOrder(OrderAPIDto order);
    Mono<String> deleteOrder(String id);
    Mono<Crm> getOrder(String id);
}
