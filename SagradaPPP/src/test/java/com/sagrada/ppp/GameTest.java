package com.sagrada.ppp;

import com.sagrada.ppp.controller.Controller;
import com.sagrada.ppp.utils.PrinterFormatter;
import com.sagrada.ppp.utils.StaticValues;
import com.sagrada.ppp.view.CliView;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GameTest {

    @Test
    public void fakeTest(){
        assertTrue(true);
    }

    @Test
    public void panelExtraction(){
        Game game = new Game("user1");
        game.joinGame("user2", null , null);
        game.joinGame("user3" , null, null);
        game.joinGame("user4" , null, null);
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
                System.out.println(PrinterFormatter.printWindowPanelContent(panel));
                //System.out.println(panel.toString());
            }
        }

    }
}
