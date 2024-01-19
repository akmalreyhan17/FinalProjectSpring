package com.finalproject.notification.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;

import com.finalproject.notification.model.Notification;
import com.finalproject.notification.model.OrderAPI;

import reactor.core.publisher.Mono;

@SpringBootTest
public class ConsumerServiceTest {

    @Mock
    NotifService notifService;

    @InjectMocks
    ConsumerService consumerService;

    @Test
    void testReceiveCreate() {
        OrderAPI data = new OrderAPI();
        data.setCrudType("CREATE");
        Message<OrderAPI> message = new GenericMessage<OrderAPI>(data);

        Notification data2 = new Notification();
        
        when(notifService.createNotification(anyString(), any())).thenReturn(Mono.just(data2));
        consumerService.receive(message);

        verify(notifService).createNotification(anyString(), any());
    }

    @Test
    void testReceiveDelete() {
        OrderAPI data = new OrderAPI();
        data.setId("1");
        data.setCrudType("DELETE");
        Message<OrderAPI> message = new GenericMessage<OrderAPI>(data);

        Notification data2 = new Notification();
        data2.setId("1");

        when(notifService.deleteReal(anyString(), any())).thenReturn(Mono.just(data2));
        consumerService.receive(message);

        verify(notifService).deleteReal(anyString(), any());
    }

    @Test
    void testReceiveNone() {
        OrderAPI data = new OrderAPI();
        data.setCrudType(null);
        Message<OrderAPI> message = new GenericMessage<OrderAPI>(data);
       
        consumerService.receive(message);
    }

    @Test
    void testReceiveUnregistered() {
        OrderAPI data = new OrderAPI();
        data.setCrudType("TEST");
        Message<OrderAPI> message = new GenericMessage<OrderAPI>(data);
       
        consumerService.receive(message);
    }
}
