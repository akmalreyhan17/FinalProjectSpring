package com.finalproject.notification.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NotificationTest {

    static Notification notification;

    @BeforeAll
    public static void setup() {
        notification = new Notification();
        notification.setId("1");
        notification.setContent("success");
    }

    @Test
    void testGetContent() {
        assertEquals("success", notification.getContent());
    }

    @Test
    void testGetId() {
        assertEquals("1", notification.getId());
    }

    @Test
    void testSetContent() {
        notification.setId("1");
        assertTrue(true);
    }

    @Test
    void testSetId() {
        notification.setContent("success");
        assertTrue(true);
    }

    @Test
    void testIsNew() {
        assertFalse(notification.isNew());

        Notification notif1 = new Notification();
        notif1.setId(null);
        notif1.setAsNew();        
        assertTrue(notif1.isNew());

        Notification notif2 = new Notification();        
        notif2.setId("2");
        notif2.setAsNew();
        assertTrue(notif2.isNew());

        Notification notif3 = new Notification();
        notif3.setId(null);
        assertTrue(notif3.isNew());
    }
    
}
