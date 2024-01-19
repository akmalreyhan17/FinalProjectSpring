package com.finalproject.crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import com.finalproject.crm.model.OrderAPI;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ConsumerService {

    private final CrmService crmService;
    private final String queue = "queue.order.crm";

    @Autowired
    public ConsumerService(CrmService crmService) {
        this.crmService = crmService;
    }

    @JmsListener(destination = queue)
    public void receive(Message<OrderAPI> message) {
        log.info("received message: {}", message);

        OrderAPI data = message.getPayload();
        log.info("crud type: {}", data.getCrudType());

        if (data.getCrudType()==null){
            log.info("No request given");
        } else if (data.getCrudType().equals("CREATE")) {
            crmService.createOrder(queue, data).subscribe();
        } else if (data.getCrudType().equals("DELETE")) {
            crmService.deleteOrder(queue, data).subscribe();
        } else {
            log.info("Request not identified: {}", data.getCrudType());
        }
    }
}
