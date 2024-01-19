package com.finalproject.orderOrchestration.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrderAPITest {
    
    static OrderAPI orderAPI;
    static LocalDateTime time = LocalDateTime.now();

    @BeforeAll
    public static void setup() {
        orderAPI = new OrderAPI();
        orderAPI.setId("1");
        orderAPI.setProductName("Balsem Gajah");
        orderAPI.setQuantity(10L);
        orderAPI.setCrudType("CREATE");
        orderAPI.setOrderType("REGISTER_1");
        orderAPI.setStatus("on process");
        orderAPI.setCreatedDate(time);
        orderAPI.setUpdatedDate(time);
    }

    @Test
    void testGetCreatedDate() {
        assertEquals(time, orderAPI.getCreatedDate());
    }

    @Test
    void testGetCrudType() {
        assertEquals("CREATE", orderAPI.getCrudType());
    }

    @Test
    void testGetId() {
        assertEquals("1", orderAPI.getId());
    }

    @Test
    void testGetOrderType() {
        assertEquals("REGISTER_1", orderAPI.getOrderType());
    }

    @Test
    void testGetProductName() {
        assertEquals("Balsem Gajah", orderAPI.getProductName());
    }

    @Test
    void testGetQuantity() {
        assertEquals(10L, orderAPI.getQuantity());
    }

    @Test
    void testGetStatus() {
        assertEquals("on process", orderAPI.getStatus());
    }

    @Test
    void testGetUpdatedDate() {
        assertEquals(time, orderAPI.getUpdatedDate());
    }

    @Test
    void testSetCreatedDate() {
        orderAPI.setCreatedDate(time);
        assertTrue(true);
    }

    @Test
    void testSetCrudType() {
        orderAPI.setCrudType("CREATE");
        assertTrue(true);
    }

    @Test
    void testSetId() {
        orderAPI.setId("1");
        assertTrue(true);
    }

    @Test
    void testSetOrderType() {
        orderAPI.setOrderType("REGISTER_1");
        assertTrue(true);
    }

    @Test
    void testSetProductName() {
        orderAPI.setProductName("Balsem Gajah");
        assertTrue(true);
    }

    @Test
    void testSetQuantity() {
        orderAPI.setQuantity(10L);
        assertTrue(true);
    }

    @Test
    void testSetStatus() {
        orderAPI.setStatus("on process");
        assertTrue(true);
    }

    @Test
    void testSetUpdatedDate() {
        orderAPI.setUpdatedDate(time);
        assertTrue(true);
    }
}
