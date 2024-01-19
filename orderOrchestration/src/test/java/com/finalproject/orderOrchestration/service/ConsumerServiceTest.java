package com.finalproject.orderOrchestration.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;

import com.finalproject.orderOrchestration.model.OrderAPI;
import com.finalproject.orderOrchestration.repository.OrchestrationRepository;
import com.finalproject.orderOrchestration.repository.TransRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootTest
public class ConsumerServiceTest {

    @Mock
    OrchestrationRepository orchestrationRepository;

    @Mock
    TransRepository transRepository;

    @Mock
    PublisherService publisherService;

    @InjectMocks
    ConsumerService consumerService;

    @Test
    void testReceiveNewOrderManyStep() {
        OrderAPI data = new OrderAPI();
        data.setId("1");
        data.setOrderType("REGISTER_1");
        data.setNewOrder(true);
        Message<OrderAPI> message = new GenericMessage<OrderAPI>(data);

        when(orchestrationRepository.getSumOfQueue(anyString())).thenReturn(Mono.just(3));
        when(orchestrationRepository.getQueue(anyString(), anyInt())).thenReturn(Mono.just("queue.test"));
        
        consumerService.receive(message);
        verify(publisherService).send(anyString(), any());
    }

    @Test
    void testReceiveNewOrderOneStep() {
        OrderAPI data = new OrderAPI();
        data.setId("1");
        data.setOrderType("REGISTER_1");
        data.setNewOrder(true);
        Message<OrderAPI> message = new GenericMessage<OrderAPI>(data);

        when(orchestrationRepository.getSumOfQueue(anyString())).thenReturn(Mono.just(1));
        when(orchestrationRepository.getQueue(anyString(), anyInt())).thenReturn(Mono.just("queue.test"));
        
        consumerService.receive(message);
        verify(publisherService).send(anyString(), any());
    }

    @Test
    void testReceiveOldOrder() {
        OrderAPI data = new OrderAPI();
        data.setId("1");
        data.setOrderType("REGISTER_1");
        data.setNewOrder(false);
        Message<OrderAPI> message = new GenericMessage<OrderAPI>(data);

        when(transRepository.getStatus(anyString())).thenReturn(Mono.just("SUCCESS"));
        consumerService.statCheck = Mono.just("RUNNING");
        consumerService.revert = Mono.just(false);
        consumerService.priority = Mono.just(1);
        consumerService.body = Mono.just(data);
        when(orchestrationRepository.getQueue(anyString(), anyInt())).thenReturn(Mono.just("queue.test"));

        consumerService.receive(message);
        verify(publisherService).send(anyString(), any());
    }

    @Test
    void testReceiveRevert() {
        OrderAPI data = new OrderAPI();
        data.setId("1");
        data.setOrderType("REGISTER_1");
        data.setNewOrder(false);
        Message<OrderAPI> message = new GenericMessage<OrderAPI>(data);

        when(transRepository.getStatus(anyString())).thenReturn(Mono.just("FAILED"));
        consumerService.statCheck = Mono.just("RUNNING");
        consumerService.revert = Mono.just(false);
        consumerService.priority = Mono.just(3);
        consumerService.body = Mono.just(data);
        when(orchestrationRepository.getRollbackQueue(anyString(), anyInt())).thenReturn(Flux.just("queue.test"));

        consumerService.receive(message);
        verify(publisherService).send(anyString(), any());
    }
}
