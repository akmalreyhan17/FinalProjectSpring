package com.finalproject.orderAPI.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.finalproject.orderAPI.model.Crm;
import com.finalproject.orderAPI.model.OrderAPIDto;
import com.finalproject.orderAPI.repository.CrmRepository;

import reactor.core.publisher.Mono;

@SpringBootTest
public class OrderServiceImplTest {

    @Mock
    PublisherService publisherService;

    @Mock
    CrmRepository crmRepository;

    @InjectMocks
    OrderServiceImpl orderServiceImpl;

    @Test
    void testCreateOrder() {
        OrderAPIDto data = new OrderAPIDto();
        data.setId("1");
        data.setProductName("Balsem Gajah");
        data.setQuantity(10L);
        data.setOrderType("REGISTER_1");

        Mono<OrderAPIDto> test = orderServiceImpl.createOrder(data);
        assertEquals(data.getId(), test.block().getId());
        verify(publisherService).send(anyString(), any());
    }

    @Test
    void testDeleteOrder() {
        Mono<String> test = orderServiceImpl.deleteOrder("1");
        assertEquals("1", test.block());
        verify(publisherService).send(anyString(), any()); 
    }

    @Test
    void testGetOrder() {
        Crm data = new Crm();
        data.setId("1");

        when(crmRepository.findById(data.getId())).thenReturn(Mono.just(data));
        Mono<Crm> test = orderServiceImpl.getOrder("1");
        assertEquals(data.getId(), test.block().getId());
    }
}
