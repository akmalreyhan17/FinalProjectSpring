package com.finalproject.notification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import com.finalproject.notification.model.OrderAPI;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ConsumerService {

    private final NotifService notifService;
    private final String queue = "queue.order.notif";

    @Autowired
    public ConsumerService(NotifService notifService) {
        this.notifService = notifService;
    }

    @JmsListener(destination = queue)
    public void receive(Message<OrderAPI> message) {
        log.info("received message: {}", message);

        OrderAPI data = message.getPayload();
        log.info("crud type: {}", data.getCrudType());

        if (data.getCrudType()==null) {
            log.info("No request given");
        } else if (data.getCrudType().equals("CREATE")) {
            notifService.createNotification(queue, data).subscribe();
        } else if (data.getCrudType().equals("DELETE")) {
            notifService.deleteReal(queue, data).subscribe();
        } else {
            log.info("Request not identified: {}", data.getCrudType());
        }
    }
}
