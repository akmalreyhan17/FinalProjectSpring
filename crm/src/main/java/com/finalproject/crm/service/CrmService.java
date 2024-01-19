package com.finalproject.crm.service;

import org.springframework.stereotype.Service;

import com.finalproject.crm.model.Crm;
import com.finalproject.crm.model.OrderAPI;

import reactor.core.publisher.Mono;

@Service
public interface CrmService {
    Mono<Crm> createOrder(String queue, OrderAPI crm);
    Mono<Crm> deleteOrder(String queue, OrderAPI crm);
}
