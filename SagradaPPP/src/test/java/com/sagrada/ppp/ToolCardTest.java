package com.sagrada.ppp;


import com.sagrada.ppp.Cards.CommandToolCard;
import com.sagrada.ppp.Cards.CommandToolCard1;
import com.sagrada.ppp.Cards.ToolCard;
import com.sagrada.ppp.Cards.ToolCard1;
import org.junit.Test;

import static org.junit.Assert.*;

public class ToolCardTest {

    @Test
    public void testAllToolCards(){
        card1();
    }


    @Test
    public void card1(){
        WindowPanel windowPanel = TestPanels.panel_50();
        WindowPanel windowResult;

        ToolCard toolCard1 = new ToolCard1();
        windowResult = toolCard1.use(new CommandToolCard1(0,1,new WindowPanel(windowPanel)));
        assertFalse(windowPanel.equals(windowResult));
        assertEquals(windowPanel.getCellWithIndex(0).getDiceOn().getValue() + 1, windowResult.getCellWithIndex(0).getDiceOn().getValue());
        windowPanel = windowResult;
        assertTrue(windowResult.equals(windowPanel));
    }
}
