package com.finalproject.orderAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finalproject.orderAPI.model.OrderAPIDto;
import com.finalproject.orderAPI.service.OrderService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/order")
public class OrderController {
    
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/new-order")
    public ResponseEntity<?> post(@RequestBody OrderAPIDto order){
        log.info("post new order: {}", order);
        return ResponseEntity.ok(orderService.createOrder(order));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id){
        log.info("delete order: {}", id);
        return ResponseEntity.ok(orderService.deleteOrder(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable("id") String id){
        log.info("get order: {}", id);
        return ResponseEntity.ok(orderService.getOrder(id));
    }
}
