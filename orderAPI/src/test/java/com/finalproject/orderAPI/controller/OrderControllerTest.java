package com.finalproject.orderAPI.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.finalproject.orderAPI.model.Crm;
import com.finalproject.orderAPI.model.OrderAPIDto;
import com.finalproject.orderAPI.service.OrderService;

import reactor.core.publisher.Mono;

@WebFluxTest
public class OrderControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private OrderService orderService;

    @Test
    void testDelete() {
        when(orderService.deleteOrder(anyString())).thenReturn(Mono.just("1"));
        webTestClient.delete().uri("/order/1")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testGet() {
        Crm data = new Crm();
        data.setId("1");

        when(orderService.getOrder(anyString())).thenReturn(Mono.just(data));
        webTestClient.get().uri("/order/1")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testPost() {
        OrderAPIDto data = new OrderAPIDto();
        data.setId("1");
        data.setProductName("Balsem Gajah");
        data.setQuantity(10L);
        data.setOrderType("REGISTER_1");

        when(orderService.createOrder(any())).thenReturn(Mono.just(data));
        webTestClient.post().uri("/order/new-order")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(data), OrderAPIDto.class)
                .exchange()
                .expectStatus().isOk();
    }
}
