package com.sagrada.ppp.cards.toolcards;

import com.sagrada.ppp.cards.TestPanels;
import com.sagrada.ppp.cards.ToolCardParameterContainer;
import com.sagrada.ppp.model.*;
import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CommandToolCard9Test {

    private ToolCard toolCard9;
    private ToolCardParameterContainer container;

    @Before
    public void setUp() {
        toolCard9 = new ToolCard9();
        container = new ToolCardParameterContainer();
        container.toolCardParameters = new ToolCardParameters();
    }

    @Test
    public void useCard() {
        WindowPanel windowPanel = TestPanels.toolCardPanel();
        Player player = new Player("test");
        player.setPanel(windowPanel);
        ArrayList<Dice> draftPool = new ArrayList<>();
        draftPool.add(new Dice(Color.YELLOW, 4));
        draftPool.add(new Dice(Color.YELLOW, 4));
        draftPool.add(new Dice(2));
        container.player = player;
        container.draftPool = draftPool;
        container.toolCardParameters.draftPoolDiceIndex = 0;
        container.toolCardParameters.panelCellIndex = 19;

        assertEquals(17, player.getPanel().getEmptyCells());

        assertTrue(toolCard9.paramsOk(container));
        toolCard9.use(container);

        assertEquals(17, player.getPanel().getEmptyCells());

        container.toolCardParameters.panelCellIndex = 0;
        assertTrue(toolCard9.paramsOk(container));
        toolCard9.use(container);

        assertEquals(16, player.getPanel().getEmptyCells());

        container.toolCardParameters.panelCellIndex = 15;
        assertFalse(toolCard9.paramsOk(container));
        assertEquals(16, player.getPanel().getEmptyCells());
    }

}