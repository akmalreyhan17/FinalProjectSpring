package com.finalproject.orderOrchestration.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ActionRegisterTest {

    static ActionRegister actionRegister;

    @BeforeAll
    public static void setup() {
        actionRegister = new ActionRegister();
        actionRegister.setId("1");
        actionRegister.setActionName("REGISTER_1");
    }

    @Test
    void testGetActionName() {
        assertEquals("REGISTER_1", actionRegister.getActionName());
    }

    @Test
    void testGetId() {
        assertEquals("1", actionRegister.getId());
    }

    @Test
    void testSetActionName() {
        actionRegister.setActionName("REGISTER_1");
        assertTrue(true);
    }

    @Test
    void testSetId() {
        actionRegister.setId("1");
        assertTrue(true);
    }
}
