package com.sagrada.ppp.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class ReconnectionResultTest {
    @Test
    public void testReconnectionResult(){
        GameStartMessage gameStartMessage = new GameStartMessage(null,null,null,null,null,null);
        ReconnectionResult reconnectionResult = new ReconnectionResult(true,"test",gameStartMessage);
        assertEquals("test",reconnectionResult.message);
        assertEquals(gameStartMessage,reconnectionResult.gameStartMessage);
        assertTrue(reconnectionResult.result);
    }

}