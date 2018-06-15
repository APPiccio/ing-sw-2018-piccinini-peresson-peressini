package com.sagrada.ppp.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class EndTurnMessageTest {

    EndTurnMessage endTurnMessage;
    Player previousPlayer;
    Player currentPlayer;
    ArrayList<Dice> draftPool;
    RoundTrack roundTrack;
    ArrayList<Player> players;

    @Before
    public void setUp() throws Exception {
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
        assertTrue(draftPool.equals(endTurnMessage.draftpool));
        assertTrue(roundTrack.equals(endTurnMessage.roundTrack));
        assertTrue(currentPlayer.equals(endTurnMessage.currentPlayer));
        assertTrue(previousPlayer.equals(endTurnMessage.previousPlayer));
        assertTrue(players.equals(endTurnMessage.players));
    }
}
