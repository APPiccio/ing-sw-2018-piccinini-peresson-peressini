package com.sagrada.ppp.model;

import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class EndTurnMessageTest {

    private EndTurnMessage endTurnMessage;
    private Player previousPlayer;
    private Player currentPlayer;
    private ArrayList<Dice> draftPool;
    private RoundTrack roundTrack;
    private ArrayList<Player> players;

    @Before
    public void setUp() {
        previousPlayer = new Player("user1");
        currentPlayer = new Player("user2");
        players = new ArrayList<>();
        players.add(previousPlayer);
        players.add(currentPlayer);
        draftPool = new ArrayList<>();
        draftPool.add(new Dice());
        draftPool.add(new Dice());
        draftPool.add(new Dice());
        draftPool.add(new Dice());
        draftPool.add(new Dice());
        roundTrack = new RoundTrack();
        endTurnMessage = new EndTurnMessage(previousPlayer, currentPlayer, players, 0, draftPool, roundTrack);
    }

    @Test
    public void testContent() {
        assertEquals(draftPool, endTurnMessage.draftpool);
        assertEquals(roundTrack, endTurnMessage.roundTrack);
        assertEquals(currentPlayer, endTurnMessage.currentPlayer);
        assertEquals(previousPlayer, endTurnMessage.previousPlayer);
        assertEquals(players, endTurnMessage.players);
    }

}