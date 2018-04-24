package com.sagrada.ppp;


import com.sagrada.ppp.Cards.*;
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
        WindowPanel windowMod = new WindowPanel(windowPanel);

        ToolCard toolCard1 = new ToolCard1();
        toolCard1.use(new CommandToolCard1(0,1,windowMod));
        assertFalse(windowPanel.equals(windowMod));
        assertEquals(windowPanel.getCellWithIndex(0).getDiceOn().getValue() + 1, windowMod.getCellWithIndex(0).getDiceOn().getValue());
        windowPanel = windowMod;
        assertTrue(windowMod.equals(windowPanel));
    }

    @Test
    public void card10(){
        WindowPanel windowPanel = TestPanels.panel_230();
        ToolCard toolCard10 = new ToolCard10();
        


    }
}
