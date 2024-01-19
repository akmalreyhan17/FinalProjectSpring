package com.finalproject.crm.service;

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

import com.finalproject.crm.model.Crm;
import com.finalproject.crm.model.OrderAPI;

import reactor.core.publisher.Mono;

@SpringBootTest
public class ConsumerServiceTest {

    @Mock
    CrmService crmService;

    @InjectMocks
    ConsumerService consumerService;

    @Test
    void testReceiveCreate() {
        OrderAPI data = new OrderAPI();
        data.setCrudType("CREATE");;
        Message<OrderAPI> message = new GenericMessage<OrderAPI>(data);

        Crm data2 = new Crm();

        when(crmService.createOrder(anyString(), any())).thenReturn(Mono.just(data2));
        consumerService.receive(message);

        verify(crmService).createOrder(anyString(), any());
    }

    @Test
    void testReceiveDelete() {
        OrderAPI data1 = new OrderAPI();
        data1.setId("1");
        data1.setCrudType("DELETE");
        Message<OrderAPI> message = new GenericMessage<OrderAPI>(data1);

        Crm data2 = new Crm();
        data2.setId(data1.getId());
        
        when(crmService.deleteOrder(anyString(), any())).thenReturn(Mono.just(data2));
        consumerService.receive(message);

        verify(crmService).deleteOrder(anyString(), any());
    }

    @Test
    void testReceiveNone() {
        OrderAPI data = new OrderAPI();
        data.setId("1");
        data.setCrudType(null);;
        Message<OrderAPI> message = new GenericMessage<OrderAPI>(data);

        consumerService.receive(message);
    }

    @Test
    void testReceiveUnregistered() {
        OrderAPI data = new OrderAPI();
        data.setId("1");
        data.setCrudType("TEST");;
        Message<OrderAPI> message = new GenericMessage<OrderAPI>(data);

        consumerService.receive(message);
    }
}
