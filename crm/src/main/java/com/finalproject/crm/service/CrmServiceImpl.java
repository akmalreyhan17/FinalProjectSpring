package com.finalproject.crm.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalproject.crm.model.Crm;
import com.finalproject.crm.model.OrderAPI;
import com.finalproject.crm.model.Trans;
import com.finalproject.crm.repository.CrmRepository;
import com.finalproject.crm.repository.TransRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class CrmServiceImpl implements CrmService {

    private final CrmRepository crmRepository;
    private final TransRepository transRepository;
    private final PublisherService publisherService;
    private final String OPERATION_SUCCESS = "SUCCESS";
    private final String OPERATION_FAILED = "FAILED";

    @Autowired
    public CrmServiceImpl(CrmRepository crmRepository, TransRepository transRepository,
            PublisherService publisherService) {
        this.crmRepository = crmRepository;
        this.transRepository = transRepository;
        this.publisherService = publisherService;
    }

    public Mono<Trans> createTransAndSend(String queue, OrderAPI data, String status) {
        Trans transaction = new Trans();
        transaction.setOrderId(data.getId());
        transaction.setQueueName(queue);
        transaction.setStatus(status);

        return transRepository.save(transaction).doOnNext(t->{
            publisherService.send("queue.order.process", data);
        });
    }

    @Override
    public Mono<Crm> createOrder(String queue, OrderAPI data) {
        Crm input = new Crm();
        input.setId(data.getId());
        input.setProductName(data.getProductName());
        input.setQuantity(data.getQuantity());
        input.setStatus(data.getStatus());
        input.setCreatedDate(LocalDateTime.now());
        input.setAsNew();

        return crmRepository.save(input).doOnNext(x -> {
            createTransAndSend(queue, data, OPERATION_SUCCESS).subscribe();
        }).doOnError(x -> {
            Mono.error(x);
            log.info("error creating data: {}", x);
            createTransAndSend(queue, data, OPERATION_FAILED).subscribe();
        });
    }

    @Override
    public Mono<Crm> deleteOrder(String queue, OrderAPI data) {

        return crmRepository.findById(data.getId()).doOnNext(x -> {
            crmRepository.delete(x).subscribe();
            createTransAndSend(queue, data, OPERATION_SUCCESS);
        }).doOnError(x -> {
            Mono.error(x);
            log.info("error deleting data: {}", x);
            createTransAndSend(queue, data, OPERATION_FAILED);
        });

    }

}
