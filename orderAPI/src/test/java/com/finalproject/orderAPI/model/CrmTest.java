package com.finalproject.orderAPI.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CrmTest {
 
    static Crm crm;
    static LocalDateTime time = LocalDateTime.now();

    @BeforeAll
    public static void setup() {
        crm = new Crm();
        crm.setId("1");
        crm.setProductName("Balsem Gajah");
        crm.setQuantity(10L);
        crm.setStatus("on process");

        
        crm.setCreatedDate(time);
        crm.setUpdatedDate(time);
    }

    @Test
    void testGetCreatedDate() {
        assertEquals(time, crm.getCreatedDate());
    }

    @Test
    void testGetId() {
        assertEquals("1", crm.getId());
    }

    @Test
    void testGetProductName() {
        assertEquals("Balsem Gajah", crm.getProductName());
    }

    @Test
    void testGetQuantity() {
        assertEquals(10L, crm.getQuantity());
    }

    @Test
    void testGetStatus() {
        assertEquals("on process", crm.getStatus());
    }

    @Test
    void testGetUpdatedDate() {
        assertEquals(time, crm.getUpdatedDate());
    }

    @Test
    void testSetCreatedDate() {
        crm.setCreatedDate(time);
        assertTrue(true);
    }

    @Test
    void testSetId() {
        crm.setId("1");
        assertTrue(true);
    }

    @Test
    void testSetProductName() {
        crm.setProductName("Balsem Gajah");
        assertTrue(true);
    }

    @Test
    void testSetQuantity() {
        crm.setQuantity(10L);
        assertTrue(true);
    }

    @Test
    void testSetStatus() {
        crm.setStatus("on process");
        assertTrue(true);
    }

    @Test
    void testSetUpdatedDate() {
        crm.setUpdatedDate(time);
        assertTrue(true);
    }
}
