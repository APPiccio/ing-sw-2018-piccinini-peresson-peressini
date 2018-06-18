package com.sagrada.ppp.model;

import com.sagrada.ppp.utils.StaticValues;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LobbyTimerTest {

    private LobbyTimer lobbyTimer;
    private Game game;
    private long startTimer;

    @Before
    public void setUp() throws Exception {
        game = new Game("CESARE_IL_SALUMIERE",null);
        startTimer = System.currentTimeMillis();
        lobbyTimer = new LobbyTimer(startTimer, game);
        lobbyTimer.start();
    }

    @Test
    public void test() {
        StaticValues.readConstants();
        lobbyTimer.interrupt();
        try {
            Thread.sleep(StaticValues.LOBBY_TIMER);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(game.isJoinable());

        startTimer = System.currentTimeMillis();
        lobbyTimer = new LobbyTimer(startTimer, game);
        lobbyTimer.start();
        try {
            Thread.sleep(StaticValues.LOBBY_TIMER + 5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertFalse(game.isJoinable());
    }

    @After
    public void tearDown() throws Exception {

    }
}