package com.sagrada.ppp.cards.toolcards;

import com.sagrada.ppp.cards.TestPanels;
import com.sagrada.ppp.cards.ToolCardParameterContainer;
import com.sagrada.ppp.model.*;
import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CommandToolCard8Test {

    private ToolCard toolCard8;
    private ToolCardParameterContainer container;
    private Player player;

    @Before
    public void setUp() {
        toolCard8 = new ToolCard8();
        container = new ToolCardParameterContainer();
        container.toolCardParameters = new ToolCardParameters();
        player = new Player("ciao");
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
        player.setPanel(windowPanel);
        container.player = player;
        container.draftPool = draftPool;
        container.toolCardParameters.draftPoolDiceIndex = 5;
        container.toolCardParameters.panelCellIndex = 1;
        assertTrue(toolCard8.paramsOk(container));
        toolCard8.use(container);
        assertTrue(true);
    }

}