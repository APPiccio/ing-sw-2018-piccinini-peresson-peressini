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
    }

    @Test
    public void useCard() {
        WindowPanel windowPanel = TestPanels.panel_2_2();
        WindowPanel windowPanelCopy = new WindowPanel(windowPanel);

        RoundTrack roundTrack = new RoundTrack();
        ArrayList<Dice> dices = new ArrayList<>();
        dices.add(new Dice(Color.BLUE));
        roundTrack.setDicesOnRound(1, dices);

        //Moving correctly 1 dice
        player.setPanel(windowPanelCopy);
        container.player = player;
        container.roundTrack = roundTrack;
        container.toolCardParameters.roundTrackRoundIndex = 1;
        container.toolCardParameters.roundTrackDiceIndex = 0;
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

        //Moving correctly 2 dices
        windowPanelCopy = new WindowPanel(windowPanel);
        player.setPanel(windowPanelCopy);
        container.player = player;
        container.roundTrack = roundTrack;
        container.toolCardParameters.roundTrackRoundIndex = 1;
        container.toolCardParameters.roundTrackDiceIndex = 0;
        container.toolCardParameters.panelDiceIndex = 12;
        container.toolCardParameters.panelCellIndex = 11;
        container.toolCardParameters.twoDiceAction = true;
        container.toolCardParameters.secondPanelDiceIndex = 2;
        container.toolCardParameters.secondPanelCellIndex = 9;
        assertTrue(toolCard12.paramsOk(container));
        toolCard12.use(container);

        //Testing that non touched cells are equals to the original panel
        for (int i = 0; i < StaticValues.NUMBER_OF_CELLS; i++) {
            if (i != 2 && i != 11 && i != 12) {
                assertEquals(windowPanel.getCell(i), windowPanelCopy.getCell(i));
            }
        }

        //Testing change
        assertEquals(windowPanel.getCell(12), windowPanelCopy.getCell(11));
        assertEquals(windowPanel.getCell(2), windowPanelCopy.getCell(9));

        //Testing paramsOk

        windowPanelCopy = new WindowPanel(windowPanel);
        player.setPanel(windowPanelCopy);

        //cell1start null
        container.player = player;
        container.roundTrack = roundTrack;
        container.toolCardParameters.roundTrackRoundIndex = 1;
        container.toolCardParameters.roundTrackDiceIndex = 0;
        container.toolCardParameters.panelDiceIndex = -42;
        container.toolCardParameters.panelCellIndex = 11;
        container.toolCardParameters.twoDiceAction = false;
        assertFalse(toolCard12.paramsOk(container));

        //cell1end null
        container.player = player;
        container.roundTrack = roundTrack;
        container.toolCardParameters.roundTrackRoundIndex = 1;
        container.toolCardParameters.roundTrackDiceIndex = 0;
        container.toolCardParameters.panelDiceIndex = 12;
        container.toolCardParameters.panelCellIndex = 777;
        container.toolCardParameters.twoDiceAction = false;
        assertFalse(toolCard12.paramsOk(container));

        //!cell1start.hasDiceOn()
        container.player = player;
        container.roundTrack = roundTrack;
        container.toolCardParameters.roundTrackRoundIndex = 1;
        container.toolCardParameters.roundTrackDiceIndex = 0;
        container.toolCardParameters.panelDiceIndex = 8;
        container.toolCardParameters.panelCellIndex = 11;
        container.toolCardParameters.twoDiceAction = false;
        assertFalse(toolCard12.paramsOk(container));

        //cell1end.hasDiceOn()
        container.player = player;
        container.roundTrack = roundTrack;
        container.toolCardParameters.roundTrackRoundIndex = 1;
        container.toolCardParameters.roundTrackDiceIndex = 0;
        container.toolCardParameters.panelDiceIndex = 12;
        container.toolCardParameters.panelCellIndex = 19;
        container.toolCardParameters.twoDiceAction = false;
        assertFalse(toolCard12.paramsOk(container));

        //cell1start has not the same color of the dice in the roundTrack
        container.player = player;
        container.roundTrack = roundTrack;
        container.toolCardParameters.roundTrackRoundIndex = 1;
        container.toolCardParameters.roundTrackDiceIndex = 0;
        container.toolCardParameters.panelDiceIndex = 1;
        container.toolCardParameters.panelCellIndex = 11;
        container.toolCardParameters.twoDiceAction = false;
        assertFalse(toolCard12.paramsOk(container));

        //Placement error dice 1
        container.player = player;
        container.roundTrack = roundTrack;
        container.toolCardParameters.roundTrackRoundIndex = 1;
        container.toolCardParameters.roundTrackDiceIndex = 0;
        container.toolCardParameters.panelDiceIndex = 0;
        container.toolCardParameters.panelCellIndex = 11;
        container.toolCardParameters.twoDiceAction = false;
        assertFalse(toolCard12.paramsOk(container));

        //cell2start null
        container.player = player;
        container.roundTrack = roundTrack;
        container.toolCardParameters.roundTrackRoundIndex = 1;
        container.toolCardParameters.roundTrackDiceIndex = 0;
        container.toolCardParameters.panelDiceIndex = 12;
        container.toolCardParameters.panelCellIndex = 11;
        container.toolCardParameters.twoDiceAction = true;
        container.toolCardParameters.secondPanelDiceIndex = -42;
        container.toolCardParameters.secondPanelCellIndex = 9;
        assertFalse(toolCard12.paramsOk(container));

        //cell2end null
        container.player = player;
        container.roundTrack = roundTrack;
        container.toolCardParameters.roundTrackRoundIndex = 1;
        container.toolCardParameters.roundTrackDiceIndex = 0;
        container.toolCardParameters.panelDiceIndex = 12;
        container.toolCardParameters.panelCellIndex = 11;
        container.toolCardParameters.twoDiceAction = true;
        container.toolCardParameters.secondPanelDiceIndex = 2;
        container.toolCardParameters.secondPanelCellIndex = 777;
        assertFalse(toolCard12.paramsOk(container));

        //!cell2start.hasDiceOn()
        container.player = player;
        container.roundTrack = roundTrack;
        container.toolCardParameters.roundTrackRoundIndex = 1;
        container.toolCardParameters.roundTrackDiceIndex = 0;
        container.toolCardParameters.panelDiceIndex = 12;
        container.toolCardParameters.panelCellIndex = 11;
        container.toolCardParameters.twoDiceAction = true;
        container.toolCardParameters.secondPanelDiceIndex = 8;
        container.toolCardParameters.secondPanelCellIndex = 9;
        assertFalse(toolCard12.paramsOk(container));

        //cell2end.hasDiceOn()
        container.player = player;
        container.roundTrack = roundTrack;
        container.toolCardParameters.roundTrackRoundIndex = 1;
        container.toolCardParameters.roundTrackDiceIndex = 0;
        container.toolCardParameters.panelDiceIndex = 12;
        container.toolCardParameters.panelCellIndex = 11;
        container.toolCardParameters.twoDiceAction = true;
        container.toolCardParameters.secondPanelDiceIndex = 2;
        container.toolCardParameters.secondPanelCellIndex = 19;
        assertFalse(toolCard12.paramsOk(container));

        //cell2start has not the same color of the dice in the roundTrack
        container.player = player;
        container.roundTrack = roundTrack;
        container.toolCardParameters.roundTrackRoundIndex = 1;
        container.toolCardParameters.roundTrackDiceIndex = 0;
        container.toolCardParameters.panelDiceIndex = 12;
        container.toolCardParameters.panelCellIndex = 11;
        container.toolCardParameters.twoDiceAction = true;
        container.toolCardParameters.secondPanelDiceIndex = 1;
        container.toolCardParameters.secondPanelCellIndex = 9;
        assertFalse(toolCard12.paramsOk(container));

        //Placement error dice 2
        container.player = player;
        container.roundTrack = roundTrack;
        container.toolCardParameters.roundTrackRoundIndex = 1;
        container.toolCardParameters.roundTrackDiceIndex = 0;
        container.toolCardParameters.panelDiceIndex = 12;
        container.toolCardParameters.panelCellIndex = 11;
        container.toolCardParameters.twoDiceAction = true;
        container.toolCardParameters.secondPanelDiceIndex = 15;
        container.toolCardParameters.secondPanelCellIndex = 9;
        assertFalse(toolCard12.paramsOk(container));

        //Trying to access an invalid round
        container.player = player;
        container.roundTrack = roundTrack;
        container.toolCardParameters.roundTrackRoundIndex = 0;
        container.toolCardParameters.roundTrackDiceIndex = 0;
        container.toolCardParameters.panelDiceIndex = 12;
        container.toolCardParameters.panelCellIndex = 11;
        container.toolCardParameters.twoDiceAction = false;
        assertFalse(toolCard12.paramsOk(container));

        //Trying to access an invalid round
        container.player = player;
        container.roundTrack = roundTrack;
        container.toolCardParameters.roundTrackRoundIndex = 20;
        container.toolCardParameters.roundTrackDiceIndex = 0;
        container.toolCardParameters.panelDiceIndex = 12;
        container.toolCardParameters.panelCellIndex = 11;
        container.toolCardParameters.twoDiceAction = false;
        assertFalse(toolCard12.paramsOk(container));

        //Trying to access a non-valid position of the round selected
        container.player = player;
        container.roundTrack = roundTrack;
        container.toolCardParameters.roundTrackRoundIndex = 1;
        container.toolCardParameters.roundTrackDiceIndex = -1;
        container.toolCardParameters.panelDiceIndex = 12;
        container.toolCardParameters.panelCellIndex = 11;
        container.toolCardParameters.twoDiceAction = false;
        assertFalse(toolCard12.paramsOk(container));

        //Trying to access a non-valid position of the round selected
        container.player = player;
        container.roundTrack = roundTrack;
        container.toolCardParameters.roundTrackRoundIndex = 1;
        container.toolCardParameters.roundTrackDiceIndex = 1;
        container.toolCardParameters.panelDiceIndex = 12;
        container.toolCardParameters.panelCellIndex = 11;
        container.toolCardParameters.twoDiceAction = false;
        assertFalse(toolCard12.paramsOk(container));

        //secondPanelCellIndex = panelCellIndex
        container.player = player;
        container.roundTrack = roundTrack;
        container.toolCardParameters.roundTrackRoundIndex = 1;
        container.toolCardParameters.roundTrackDiceIndex = 0;
        container.toolCardParameters.panelDiceIndex = 12;
        container.toolCardParameters.panelCellIndex = 11;
        container.toolCardParameters.twoDiceAction = true;
        container.toolCardParameters.secondPanelDiceIndex = 2;
        container.toolCardParameters.secondPanelCellIndex = 11;
        assertFalse(toolCard12.paramsOk(container));

        //panelDiceIndex = secondPanelDiceIndex
        container.player = player;
        container.roundTrack = roundTrack;
        container.toolCardParameters.roundTrackRoundIndex = 1;
        container.toolCardParameters.roundTrackDiceIndex = 0;
        container.toolCardParameters.panelDiceIndex = 12;
        container.toolCardParameters.panelCellIndex = 11;
        container.toolCardParameters.twoDiceAction = true;
        container.toolCardParameters.secondPanelDiceIndex = 12;
        container.toolCardParameters.secondPanelCellIndex = 9;
        assertFalse(toolCard12.paramsOk(container));
    }

}