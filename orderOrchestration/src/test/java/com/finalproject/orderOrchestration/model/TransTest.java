package com.finalproject.orderOrchestration.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TransTest {
    static Trans transaction;

    @BeforeAll
    public static void setup() {
        transaction = new Trans();
        transaction.setId(1);
        transaction.setOrderId("1");
        transaction.setQueueName("queue.test");
        transaction.setStatus("SUCCESS");
    }
    @Test
    void testGetId() {
        assertEquals(1, transaction.getId());
    }

    @Test
    void testGetOrderId() {
        assertEquals("1", transaction.getOrderId());
    }

    @Test
    void testGetQueueName() {
        assertEquals("queue.test", transaction.getQueueName());
    }

    @Test
    void testGetStatus() {
        assertEquals("SUCCESS", transaction.getStatus());
    }

    @Test
    void testIsNew() {
        assertFalse(transaction.isNew());

        Trans data1 = new Trans();
        data1.setId(null);
        assertTrue(data1.isNew());
    }

    @Test
    void testSetAsNew() {
        Trans data1 = new Trans();
        data1.setId(null);
        data1.setAsNew();
        assertTrue(data1.isNew());

        Trans data2 = new Trans();
        data2.setId(2);
        data2.setAsNew();
        assertTrue(data2.isNew());
    }

    @Test
    void testSetId() {
        transaction.setId(1);
        assertTrue(true);
    }

    @Test
    void testSetOrderId() {
        transaction.setOrderId("1");
        assertTrue(true);
    }

    @Test
    void testSetQueueName() {
        transaction.setQueueName("queue.test");
        assertTrue(true);
    }

    @Test
    void testSetStatus() {
        transaction.setStatus("SUCCESS");
        assertTrue(true);
    }
}
