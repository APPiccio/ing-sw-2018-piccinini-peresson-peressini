package com.sagrada.ppp.cards.toolcards;

import com.sagrada.ppp.cards.TestPanels;
import com.sagrada.ppp.cards.ToolCardParameterContainer;
import com.sagrada.ppp.model.Player;
import com.sagrada.ppp.model.PlayerScore;
import com.sagrada.ppp.model.ToolCardParameters;
import com.sagrada.ppp.model.WindowPanel;
import com.sagrada.ppp.utils.StaticValues;
import javafx.util.Pair;
import org.junit.*;

import static org.junit.Assert.*;

public class CommandToolCard2Test {

    private ToolCard toolCard2;
    private ToolCardParameterContainer container;
    private Player player;

    @Before
    public void setUp() {
        toolCard2 = new ToolCard2();
        container = new ToolCardParameterContainer();
        container.toolCardParameters = new ToolCardParameters();
        player = new Player("ciao");
        player.setPanel( new WindowPanel(1, 0));

    }

    @Test
    public void useCard() {
        WindowPanel windowPanel = TestPanels.toolCardPanel();
        WindowPanel windowPanelCopy = new WindowPanel(windowPanel);
        player.setPanel(windowPanelCopy);
        container.toolCardParameters.panelDiceIndex = 13;
        container.toolCardParameters.panelCellIndex = 6;
        container.player = player;
        toolCard2.paramsOk(container);
        toolCard2.use(container);

        //Testing non-touched cells
        for (int i = 0; i < StaticValues.NUMBER_OF_CELLS; i++) {
            if (i != 6 && i != 13) {
                assertEquals(windowPanel.getCell(i), windowPanelCopy.getCell(i));
            }
        }

        //Testing changes
        assertEquals(windowPanel.getCell(13).getDiceOn().getValue(), windowPanelCopy.getCell(6).getDiceOn().getValue());
        assertEquals(windowPanel.getCell(13).getDiceOn().getColor(), windowPanelCopy.getCell(6).getDiceOn().getColor());
    }

}