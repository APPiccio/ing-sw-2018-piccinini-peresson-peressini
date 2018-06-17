package com.sagrada.ppp.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class JoinGameResultTest {

    JoinGameResult joinGameResult;
    private int playerHashCode;
    private int gameHashCode;
    private String username;
    private long timerStart;
    private ArrayList<String> playersUsername;


    @Before
    public void setUp() throws Exception {
        playerHashCode = 10101010;
        gameHashCode = 010101010;
        username = "pippo";
        timerStart = 50154501;
        playersUsername = new ArrayList<>();
        playersUsername.add(username);
        joinGameResult = new JoinGameResult(playerHashCode, gameHashCode, username, timerStart, playersUsername);
    }

    @Test
    public void testContent() {
        assertEquals(playerHashCode, joinGameResult.getPlayerHashCode());
        assertEquals(gameHashCode, joinGameResult.getGameHashCode());
        assertEquals(timerStart, joinGameResult.getTimerStart());
        assertEquals(username, joinGameResult.getUsername());
        assertEquals(playersUsername, joinGameResult.getPlayersUsername());

        JoinGameResult jgr2 = new JoinGameResult(joinGameResult);
        assertEquals(playerHashCode, jgr2.getPlayerHashCode());
        assertEquals(gameHashCode, jgr2.getGameHashCode());
        assertEquals(timerStart, jgr2.getTimerStart());
        assertEquals(username, jgr2.getUsername());
        assertEquals(playersUsername, jgr2.getPlayersUsername());

        jgr2 = new JoinGameResult(playerHashCode, gameHashCode, username, playersUsername);
        assertEquals(playerHashCode, jgr2.getPlayerHashCode());
        assertEquals(gameHashCode, jgr2.getGameHashCode());
        assertEquals(0, jgr2.getTimerStart());
        assertEquals(username, jgr2.getUsername());
        assertEquals(playersUsername, jgr2.getPlayersUsername());

        gameHashCode = 123123;
        playerHashCode = 321312;
        playersUsername.add("33trentini");
        timerStart = 789789;
        username = "tornarono_a_trento";

        jgr2.setGameHashCode(gameHashCode);
        jgr2.setPlayerHashCode(playerHashCode);
        jgr2.setPlayersUsername(playersUsername);
        jgr2.setUsername(username);
        jgr2.setTimerStart(timerStart);

        assertEquals(playerHashCode, jgr2.getPlayerHashCode());
        assertEquals(gameHashCode, jgr2.getGameHashCode());
        assertEquals(timerStart, jgr2.getTimerStart());
        assertEquals(username, jgr2.getUsername());
        assertEquals(playersUsername, jgr2.getPlayersUsername());

    }
}