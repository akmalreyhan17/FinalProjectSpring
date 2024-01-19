package com.finalproject.crm.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.finalproject.crm.model.Crm;
import com.finalproject.crm.model.OrderAPI;
import com.finalproject.crm.model.Trans;
import com.finalproject.crm.repository.CrmRepository;
import com.finalproject.crm.repository.TransRepository;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
public class CrmServiceImplTest {

    @Mock
    CrmRepository crmRepository;

    @Mock
    TransRepository transRepository; 

    @Mock
    PublisherService publisherService;

    @InjectMocks
    CrmServiceImpl crmServiceImpl;

    @Test
    void testCreateOrder() {
        OrderAPI data1 = new OrderAPI();
        data1.setId("1");

        Crm data = new Crm();
        data.setId(data1.getId());

        Trans data2 = new Trans();
        data2.setOrderId("1");

        when(transRepository.save(any())).thenReturn(Mono.just(data2));
        when(crmRepository.save(any())).thenReturn(Mono.just(data));
        Mono<Crm> data3 = crmServiceImpl.createOrder("queue.test", data1);
        assertEquals(data1.getId(), data3.block().getId());
    }

    @Test
    void testCreateOrderError() {
        OrderAPI data1 = new OrderAPI();
        data1.setId("1");

        Trans data2 = new Trans();
        data2.setOrderId("1");

        when(transRepository.save(any())).thenReturn(Mono.just(data2));
        doReturn(Mono.error(Exception::new)).when(crmRepository).save(any());
        StepVerifier.create(crmServiceImpl.createOrder("queue.test", data1)).verifyError();
    }

    @Test
    void testDeleteOrder() {
        OrderAPI data1 = new OrderAPI();
        data1.setId("1");

        Crm data2 = new Crm();
        data2.setId("1");

        Trans data3 = new Trans();
        data3.setOrderId("1");

        when(transRepository.save(any())).thenReturn(Mono.just(data3));
        when(crmRepository.findById(anyString())).thenReturn(Mono.just(data2));
        when(crmRepository.delete(any())).thenReturn(Mono.empty());
        Mono<Crm> data = crmServiceImpl.deleteOrder("queue.test", data1);

        assertEquals(data2.getId(), data.block().getId());
    }
    
    @Test
    void testDeleteOrderError() {
        OrderAPI data1 = new OrderAPI();
        data1.setId("1");

        Trans data2 = new Trans();
        data2.setOrderId("1");

        when(transRepository.save(any())).thenReturn(Mono.just(data2));
        doReturn(Mono.error(Exception::new)).when(crmRepository).findById(anyString());
        StepVerifier.create(crmServiceImpl.deleteOrder("queue.test", data1)).verifyError();
    }
}
