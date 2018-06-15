package com.sagrada.ppp.model;

import com.sagrada.ppp.WindowPanelTest;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class DicePlacedMessageTest {

    DicePlacedMessage dicePlacedMessage;
    String username;
    WindowPanel windowPanel;
    ArrayList<Dice> draftPool;

    @Before
    public void setUp() throws Exception {
        username = "u_s_e_r";
        windowPanel = WindowPanelTest.generateBlankPanel();
        draftPool = new ArrayList<>();
        draftPool.add(new Dice());
        draftPool.add(new Dice());
        draftPool.add(new Dice());
        draftPool.add(new Dice());
        draftPool.add(new Dice());
        dicePlacedMessage = new DicePlacedMessage(username, windowPanel, draftPool);
    }

    @Test
    public void testContet() {
        assertTrue(username.equals(dicePlacedMessage.username));
        assertTrue(draftPool.equals(dicePlacedMessage.draftPool));
        assertTrue(windowPanel.equals(dicePlacedMessage.panel));
    }
}
