package com.finalproject.notification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalproject.notification.model.Notification;
import com.finalproject.notification.model.OrderAPI;
import com.finalproject.notification.model.Trans;
import com.finalproject.notification.repository.NotifRepository;
import com.finalproject.notification.repository.TransRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class NotifServiceImpl implements NotifService {

    private final NotifRepository notifRepository;
    private final TransRepository transRepository;    
    private final PublisherService publisherService;
    private final String OPERATION_SUCCESS = "SUCCESS";
    private final String OPERATION_FAILED = "FAILED";

    @Autowired
    public NotifServiceImpl(NotifRepository notifRepository, TransRepository transRepository, PublisherService publisherService) {
        this.notifRepository = notifRepository;
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
    public Mono<Notification> createNotification(String queue, OrderAPI data) {
        Notification notif = new Notification();
        notif.setId(data.getId());
        notif.setContent("Pesanan " + data.getProductName().toString() + " sejumlah "
                + data.getQuantity().toString() + " berhasil dibuat");
        notif.setAsNew();

        return notifRepository.save(notif).doOnNext(x -> {
            createTransAndSend(queue, data, OPERATION_SUCCESS).subscribe();
        }).doOnError(x -> {
            log.info("error creating notification: {}", x);
            createTransAndSend(queue, data, OPERATION_FAILED).subscribe();
        });
    }

    @Override
    public Mono<Notification> deleteReal(String queue, OrderAPI notif) {
        return notifRepository.findById(notif.getId()).doOnNext(x->{
            notifRepository.delete(x).subscribe();
            createTransAndSend(queue, notif, OPERATION_SUCCESS).subscribe();
        }).doOnError(x->{
            log.info("error updating notification: {}", x);
            createTransAndSend(queue, notif, OPERATION_FAILED).subscribe();
        });
    }

}
