package com.finalproject.notification.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.finalproject.notification.model.Notification;
import com.finalproject.notification.model.OrderAPI;
import com.finalproject.notification.model.Trans;
import com.finalproject.notification.repository.NotifRepository;
import com.finalproject.notification.repository.TransRepository;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
public class NotifServiceImplTest {

    @Mock
    NotifRepository notifRepository;

    @Mock
    TransRepository transRepository;

    @Mock
    PublisherService publisherService;

    @InjectMocks
    NotifServiceImpl notifServiceImpl;
    
    @Test
    void testCreateNotification() {
        OrderAPI data1 = new OrderAPI();
        data1.setId("1");
        data1.setProductName("Balsem Gajah");
        data1.setQuantity(10L);

        Notification data = new Notification();
        data.setId(data1.getId());

        Trans data2 = new Trans();
        data2.setOrderId("1");

        when(notifRepository.save(any())).thenReturn(Mono.just(data));
        when(transRepository.save(any())).thenReturn(Mono.just(data2));
        Mono<Notification> test = notifServiceImpl.createNotification("queue.test",data1);
        assertEquals(data.getId(), test.block().getId());
    }

    @Test
    void testCreateNotificationError() {
        OrderAPI data1 = new OrderAPI();
        data1.setId("1");
        data1.setProductName("Balsem Gajah");
        data1.setQuantity(10L);

        Notification data = new Notification();
        data.setId(data1.getId());

        Trans data2 = new Trans();
        data2.setOrderId("1");

        doReturn(Mono.error(Exception::new)).when(notifRepository).save(any());
        when(transRepository.save(any())).thenReturn(Mono.just(data2));
        StepVerifier.create(notifServiceImpl.createNotification("queue.test", data1)).verifyError();
    }

    @Test
    void testDeleteNotification() {
        OrderAPI data1 = new OrderAPI();
        data1.setId("1");

        Notification data = new Notification();
        data.setId(data1.getId());

        Trans data2 = new Trans();
        data2.setOrderId("1");

        when(notifRepository.findById(anyString())).thenReturn(Mono.just(data));
        when(notifRepository.delete(any())).thenReturn(Mono.empty());
        when(transRepository.save(any())).thenReturn(Mono.just(data2));
        Mono<Notification> test = notifServiceImpl.deleteReal("queue.test", data1);
        assertEquals(data.getId(), test.block().getId());
    }

    @Test
    void testDeleteNotificationError() {
        OrderAPI data1 = new OrderAPI();
        data1.setId("1");

        Trans data2 = new Trans();
        data2.setOrderId("1");

        doReturn(Mono.error(Exception::new)).when(notifRepository).findById(anyString());
        when(transRepository.save(any())).thenReturn(Mono.just(data2));
        StepVerifier.create(notifServiceImpl.deleteReal("queue.test", data1)).verifyError();
    }
}
