package com.finalproject.orderAPI.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class PublisherService {
    
    private final JmsTemplate jmsTemplate;
    
    @Autowired
    public PublisherService(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public Mono<Void> send(String queueName, Object object){
        log.info("send message to {} data: {}", queueName, object);
        jmsTemplate.convertAndSend(queueName, object);
        return Mono.empty();
    }
}
