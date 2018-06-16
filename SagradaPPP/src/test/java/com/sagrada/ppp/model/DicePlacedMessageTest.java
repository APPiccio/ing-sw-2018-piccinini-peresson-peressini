package com.sagrada.ppp.model;

import com.sagrada.ppp.TestPanels;
import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class DicePlacedMessageTest {

    private DicePlacedMessage dicePlacedMessage;
    private String username;
    private WindowPanel windowPanel;
    private ArrayList<Dice> draftPool;

    @Before
    public void setUp() {
        username = "u_s_e_r";
        windowPanel = TestPanels.createBlankPanel();
        draftPool = new ArrayList<>();
        draftPool.add(new Dice());
        draftPool.add(new Dice());
        draftPool.add(new Dice());
        draftPool.add(new Dice());
        draftPool.add(new Dice());
        dicePlacedMessage = new DicePlacedMessage(username, windowPanel, draftPool);
    }

    @Test
    public void testContent() {
        assertEquals(username, dicePlacedMessage.username);
        assertEquals(draftPool, dicePlacedMessage.draftPool);
        assertEquals(windowPanel, dicePlacedMessage.panel);
    }

}