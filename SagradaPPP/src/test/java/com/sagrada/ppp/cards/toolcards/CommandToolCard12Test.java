package com.sagrada.ppp.cards.toolcards;

import com.sagrada.ppp.cards.TestPanels;
import com.sagrada.ppp.cards.ToolCardParameterContainer;
import com.sagrada.ppp.model.*;
import com.sagrada.ppp.utils.StaticValues;
import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CommandToolCard12Test {

    private ToolCard toolCard12;
    private ToolCardParameterContainer container;
    private Player player;

    @Before
    public void setUp() {
        toolCard12 = new ToolCard12();
        container = new ToolCardParameterContainer();
        container.toolCardParameters = new ToolCardParameters();
        player = new Player("ciao");
        player.setPanel( new WindowPanel(1, 0));
    }
    @Test
    public void useCard() {
        WindowPanel windowPanel = TestPanels.panel_2_2();
        WindowPanel windowPanelCopy = new WindowPanel(windowPanel);
        RoundTrack roundTrack = new RoundTrack();
        ArrayList<Dice> dices = new ArrayList<>();
        dices.add(new Dice(Color.BLUE));
        roundTrack.setDicesOnRound(1, dices);
        player.setPanel(windowPanelCopy);
        container.player = player;
        container.roundTrack = roundTrack;
        container.toolCardParameters.roundTrackDiceIndex = 0;
        container.toolCardParameters.roundTrackRoundIndex = 1;
        container.toolCardParameters.panelDiceIndex = 12;
        container.toolCardParameters.panelCellIndex = 11;
        container.toolCardParameters.twoDiceAction = false;
        assertTrue(toolCard12.paramsOk(container));
        toolCard12.use(container);
        //Testing that non touched cells are equals to the original panel
        for (int i = 0; i < StaticValues.NUMBER_OF_CELLS; i++) {
            if (i != 12 && i != 11) {
                assertEquals(windowPanel.getCell(i), windowPanelCopy.getCell(i));
            }
        }

        //Testing change
        assertEquals(windowPanel.getCell(12), windowPanelCopy.getCell(11));

        windowPanelCopy = new WindowPanel(windowPanel);
        player.setPanel(windowPanelCopy);
        container.player = player;
        container.toolCardParameters.secondPanelDiceIndex = 11;
        container.toolCardParameters.secondPanelCellIndex = 12;
        container.toolCardParameters.twoDiceAction = true;
        assertTrue(toolCard12.paramsOk(container));

        for (int i = 0; i < StaticValues.NUMBER_OF_CELLS; i++) {
            assertEquals(windowPanel.getCell(i), windowPanelCopy.getCell(i));
        }
    }

}