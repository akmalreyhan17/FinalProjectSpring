package com.finalproject.orderAPI.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalproject.orderAPI.model.Crm;
import com.finalproject.orderAPI.model.OrderAPI;
import com.finalproject.orderAPI.model.OrderAPIDto;
import com.finalproject.orderAPI.repository.CrmRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    
    private final PublisherService publisherService;
    private final CrmRepository crmRepository;

    @Autowired
    public OrderServiceImpl(PublisherService publisherService, CrmRepository crmRepository) {
        this.publisherService = publisherService;
        this.crmRepository = crmRepository;
    }
    
    @Override
    public Mono<OrderAPIDto> createOrder(OrderAPIDto order) {
        OrderAPI data = new OrderAPI();
        data.setId(order.getId());
        data.setProductName(order.getProductName());
        data.setQuantity(order.getQuantity());
        data.setStatus("on process");
        data.setCrudType("CREATE");
        data.setOrderType(order.getOrderType());
        publisherService.send("queue.order.process", data);
        
        log.info("sent create order: {}", order);
        return Mono.just(order);
    }

    @Override
    public Mono<String> deleteOrder(String id) {
        OrderAPI data = new OrderAPI();
        data.setId(id);
        data.setCrudType("DELETE");
        publisherService.send("queue.order.process", data);
        
        log.info("sent delete order: {}", id);
        return Mono.just(id);
    }

    @Override
    public Mono<Crm> getOrder(String id) {
        return crmRepository.findById(id);
    }
    
}
