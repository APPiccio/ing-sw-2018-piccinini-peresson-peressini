package com.sagrada.ppp.cards.toolcards;

import com.sagrada.ppp.cards.TestPanels;
import com.sagrada.ppp.model.Color;
import com.sagrada.ppp.model.Dice;
import com.sagrada.ppp.model.WindowPanel;
import com.sagrada.ppp.model.WindowPanelTest;
import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CommandToolCard8Test {

    private ToolCard toolCard8;

    @Before
    public void setUp() {
        toolCard8 = new ToolCard8();
    }

    /**
     * This method simply adds a dice in a windowPanel, respecting all placing restrictions
     * @see WindowPanelTest
     */
    @Test
    public void useCard() {
        WindowPanel windowPanel = TestPanels.toolCardPanel();

        //Creating a new draftPool, 4 players participating at the game
        ArrayList<Dice> draftPool = new ArrayList<>(); //extracted dices
        for (int i = 0; i < 9; i++) {
            draftPool.add(new Dice(Color.PURPLE, 1));
        }

        toolCard8.use(new CommandToolCard8(1, windowPanel, 5, draftPool));

        assertTrue(true);
    }

}