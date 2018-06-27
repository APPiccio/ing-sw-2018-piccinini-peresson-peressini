package com.sagrada.ppp.cards.toolcards;

import com.sagrada.ppp.cards.TestPanels;
import com.sagrada.ppp.cards.ToolCardParameterContainer;
import com.sagrada.ppp.model.Player;
import com.sagrada.ppp.model.ToolCardParameters;
import com.sagrada.ppp.model.WindowPanel;
import com.sagrada.ppp.utils.StaticValues;
import org.junit.*;

import static org.junit.Assert.*;

public class CommandToolCard4Test {

    private ToolCard toolCard4;
    private ToolCardParameterContainer container;
    private Player player;

    @Before
    public void setUp() {
        toolCard4 = new ToolCard4();
        container = new ToolCardParameterContainer();
        container.toolCardParameters = new ToolCardParameters();
        player = new Player("ciao");
        player.setPanel( new WindowPanel(1, 0));
    }

    @Test
    public void useCard() {
        WindowPanel windowPanel = TestPanels.panel_2_2();
        WindowPanel windowPanelCopy = new WindowPanel(windowPanel);
        player.setPanel(windowPanelCopy);
        container.toolCardParameters.panelDiceIndex = 2;
        container.toolCardParameters.panelCellIndex = 9;
        container.toolCardParameters.secondPanelDiceIndex = 12;
        container.toolCardParameters.secondPanelCellIndex = 11;
        container.player = player;
        assertTrue( toolCard4.paramsOk(container));
        toolCard4.use(container);

        //Testing non-touched cells
        for (int i = 0; i < StaticValues.NUMBER_OF_CELLS; i++) {
            if (i != 2 && i != 9 && i != 11 && i != 12) {
                assertEquals(windowPanel.getCell(i), windowPanelCopy.getCell(i));
            }
        }

        //Testing changes
        assertEquals(windowPanel.getCell(12), windowPanelCopy.getCell(11));
        assertEquals(windowPanel.getCell(2), windowPanelCopy.getCell(9));

        container.toolCardParameters.panelDiceIndex = 9;
        container.toolCardParameters.panelCellIndex = 2;
        container.toolCardParameters.secondPanelDiceIndex = 11;
        container.toolCardParameters.secondPanelCellIndex = 12;
        assertTrue( toolCard4.paramsOk(container));
        toolCard4.use(container);

        for (int i = 0; i < StaticValues.NUMBER_OF_CELLS; i++) {
            assertEquals(windowPanel.getCell(i), windowPanelCopy.getCell(i));
        }

        //Testing paramsOk
        player.setPanel(null);
        container.toolCardParameters.panelDiceIndex = 2;
        container.toolCardParameters.panelCellIndex = 9;
        container.toolCardParameters.secondPanelDiceIndex = 12;
        container.toolCardParameters.secondPanelCellIndex = 11;
        container.player = player;
        assertFalse(toolCard4.paramsOk(container));

        windowPanelCopy = new WindowPanel(windowPanel);
        player.setPanel(windowPanelCopy);

        container.toolCardParameters.panelDiceIndex = 2;
        container.toolCardParameters.panelCellIndex = -777;
        container.toolCardParameters.secondPanelDiceIndex = 12;
        container.toolCardParameters.secondPanelCellIndex = 11;
        container.player = player;
        assertFalse(toolCard4.paramsOk(container));

        container.toolCardParameters.panelDiceIndex = 2;
        container.toolCardParameters.panelCellIndex = 42;
        container.toolCardParameters.secondPanelDiceIndex = 12;
        container.toolCardParameters.secondPanelCellIndex = 11;
        container.player = player;
        assertFalse(toolCard4.paramsOk(container));

        container.toolCardParameters.panelDiceIndex = 2;
        container.toolCardParameters.panelCellIndex = 9;
        container.toolCardParameters.secondPanelDiceIndex = 12;
        container.toolCardParameters.secondPanelCellIndex = -777;
        container.player = player;
        assertFalse(toolCard4.paramsOk(container));

        container.toolCardParameters.panelDiceIndex = 2;
        container.toolCardParameters.panelCellIndex = 9;
        container.toolCardParameters.secondPanelDiceIndex = 12;
        container.toolCardParameters.secondPanelCellIndex = 42;
        container.player = player;
        assertFalse(toolCard4.paramsOk(container));

        container.toolCardParameters.panelDiceIndex = 42;
        container.toolCardParameters.panelCellIndex = 9;
        container.toolCardParameters.secondPanelDiceIndex = 12;
        container.toolCardParameters.secondPanelCellIndex = 11;
        container.player = player;
        assertFalse(toolCard4.paramsOk(container));

        container.toolCardParameters.panelDiceIndex = 2;
        container.toolCardParameters.panelCellIndex = 9;
        container.toolCardParameters.secondPanelDiceIndex = -777;
        container.toolCardParameters.secondPanelCellIndex = 11;
        container.player = player;
        assertFalse(toolCard4.paramsOk(container));

        container.toolCardParameters.panelDiceIndex = 8;
        container.toolCardParameters.panelCellIndex = 9;
        container.toolCardParameters.secondPanelDiceIndex = 12;
        container.toolCardParameters.secondPanelCellIndex = 11;
        container.player = player;
        assertFalse(toolCard4.paramsOk(container));

        container.toolCardParameters.panelDiceIndex = 2;
        container.toolCardParameters.panelCellIndex = 0;
        container.toolCardParameters.secondPanelDiceIndex = 12;
        container.toolCardParameters.secondPanelCellIndex = 11;
        container.player = player;
        assertFalse(toolCard4.paramsOk(container));

        container.toolCardParameters.panelDiceIndex = 2;
        container.toolCardParameters.panelCellIndex = 9;
        container.toolCardParameters.secondPanelDiceIndex = 8;
        container.toolCardParameters.secondPanelCellIndex = 11;
        container.player = player;
        assertFalse(toolCard4.paramsOk(container));

        container.toolCardParameters.panelDiceIndex = 2;
        container.toolCardParameters.panelCellIndex = 9;
        container.toolCardParameters.secondPanelDiceIndex = 12;
        container.toolCardParameters.secondPanelCellIndex = 0;
        container.player = player;
        assertFalse(toolCard4.paramsOk(container));
    }

}