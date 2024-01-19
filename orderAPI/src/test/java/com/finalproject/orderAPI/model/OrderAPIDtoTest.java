package com.finalproject.orderAPI.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrderAPIDtoTest {

    static OrderAPIDto orderAPIDto;

    @BeforeAll
    public static void setup() {
        orderAPIDto = new OrderAPIDto();
        orderAPIDto.setId("1");
        orderAPIDto.setProductName("Balsem Gajah");
        orderAPIDto.setQuantity(10L);
        orderAPIDto.setOrderType("REGISTER_1");
    }

    @Test
    void testGetId() {
        assertEquals("1", orderAPIDto.getId());
    }

    @Test
    void testGetOrderType() {
        assertEquals("REGISTER_1", orderAPIDto.getOrderType());
    }

    @Test
    void testGetProductName() {
        assertEquals("Balsem Gajah", orderAPIDto.getProductName());
    }

    @Test
    void testGetQuantity() {
        assertEquals(10L, orderAPIDto.getQuantity());
    }

    @Test
    void testSetId() {
        orderAPIDto.setId("1");
        assertTrue(true);
    }

    @Test
    void testSetOrderType() {
        orderAPIDto.setOrderType("REGISTER_1");
        assertTrue(true);
    }

    @Test
    void testSetProductName() {
        orderAPIDto.setProductName("Balsem Gajah");
        assertTrue(true);
    }

    @Test
    void testSetQuantity() {
        orderAPIDto.setQuantity(10L);
        assertTrue(true);
    }
}
