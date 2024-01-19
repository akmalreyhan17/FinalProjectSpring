package com.finalproject.notification.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;

import com.finalproject.notification.model.OrderAPI;

@SpringBootTest
public class PublisherServiceTest {
    
    @Mock
    private JmsTemplate jmsTemplate;

    @InjectMocks
    PublisherService publisherService;

    @Test
    void testSend() {
        OrderAPI data = new OrderAPI();
        data.setId("1");

        publisherService.send("queue.test", data);
        verify(jmsTemplate).convertAndSend(anyString(), any(OrderAPI.class));
    }
}
