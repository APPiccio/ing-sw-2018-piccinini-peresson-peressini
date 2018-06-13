package com.sagrada.ppp;

import com.sagrada.ppp.model.Game;
import com.sagrada.ppp.model.WindowPanel;
import org.junit.Before;
import org.junit.Test;

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
        game.joinGame("user4" , null);
        game.setTurn(1);
    }

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
