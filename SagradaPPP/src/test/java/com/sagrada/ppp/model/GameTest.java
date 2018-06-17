package com.sagrada.ppp.model;

import org.junit.*;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

public class GameTest {
    private Game game;

    @Before
    public void init(){
        game = new Game("user1");
        game.joinGame("user2", null);
        game.joinGame("user3" , null);
        game.joinGame("user1" , null);
        assertFalse(game.isJoinable());
    }

/*
    @Test
    public void playersTest() {
        ArrayList<Player> players = game.getPlayers();
        assertEquals(players.stream().filter(x -> x.getPlayerStatus().equals(PlayerStatus.ACTIVE)).count(), game.getActivePlayersNumber());
        for (Player player : players){
            assertEquals(player.getUsername(), game.getPlayerUsername(player.getHashCode()));
            assertEquals(player.getHashCode(), game.getPlayerHashCode(player.getUsername()));
        }
    }

*/

    @Test
    public void panelExtraction(){
        ArrayList<Integer> cardAlreadyExtracted = new ArrayList<>();
        HashMap<Integer, ArrayList<WindowPanel>> panelsPerPlayer = game.extractPanels();
        for(Integer i : panelsPerPlayer.keySet()) {
            assertTrue(!cardAlreadyExtracted.contains(panelsPerPlayer.get(i).get(0).getCardID()));
            assertTrue(!cardAlreadyExtracted.contains(panelsPerPlayer.get(i).get(2).getCardID()));
            cardAlreadyExtracted.add(panelsPerPlayer.get(i).get(0).getCardID());
            cardAlreadyExtracted.add(panelsPerPlayer.get(i).get(2).getCardID());
            assertEquals(4, panelsPerPlayer.get(i).size());
            assertEquals(panelsPerPlayer.get(i).get(0).getCardID(), panelsPerPlayer.get(i).get(1).getCardID());
            assertEquals(panelsPerPlayer.get(i).get(2).getCardID(), panelsPerPlayer.get(i).get(3).getCardID());
            assertTrue(!(panelsPerPlayer.get(i).get(1).getCardID() == panelsPerPlayer.get(i).get(2).getCardID()));
            for (WindowPanel panel : panelsPerPlayer.get(i)) {
                System.out.println(panel.toString());
            }
        }

    }

    @Test
    public void testPlayerShift(){

        assertEquals(game.getCurrentPlayerIndex(), 0);
        game.setTurn(2);
        assertEquals(game.getCurrentPlayerIndex(), 1);
        game.setTurn(3);
        assertEquals(game.getCurrentPlayerIndex(), 2);
        game.setTurn(4);
        assertEquals(game.getCurrentPlayerIndex(), 3);
        game.setTurn(5);
        assertEquals(game.getCurrentPlayerIndex(), 3);
        game.setTurn(6);
        assertEquals(game.getCurrentPlayerIndex(), 2);
        game.setTurn(7);
        assertEquals(game.getCurrentPlayerIndex(), 1);
        game.setTurn(8);
        assertEquals(game.getCurrentPlayerIndex(), 0);

    }
}
