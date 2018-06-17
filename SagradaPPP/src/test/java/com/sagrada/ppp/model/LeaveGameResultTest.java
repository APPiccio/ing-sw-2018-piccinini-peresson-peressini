package com.sagrada.ppp.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class LeaveGameResultTest {

    private LeaveGameResult leaveGameResult;
    private LeaveGameResultStatus status;
    private int gameHashCode;


    @Before
    public void setUp() throws Exception {
        gameHashCode = 12312312;
        status = LeaveGameResultStatus.GAME_DELETED;

        leaveGameResult = new LeaveGameResult(gameHashCode, status);
    }

    @Test
    public void testContent() {
        assertEquals(gameHashCode, leaveGameResult.getGameHashCode());
        assertEquals(status, leaveGameResult.getStatus());

        status = LeaveGameResultStatus.FAIL;
        gameHashCode = 777;
        leaveGameResult.setStatus(status);
        leaveGameResult.setGameHashCode(gameHashCode);

        assertEquals(gameHashCode, leaveGameResult.getGameHashCode());
        assertEquals(status, leaveGameResult.getStatus());

    }
}