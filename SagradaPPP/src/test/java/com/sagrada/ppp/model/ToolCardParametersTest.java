package com.sagrada.ppp.model;


import org.junit.*;

import static org.junit.Assert.*;

public class ToolCardParametersTest {
    ToolCardParameters toolCardParameters;

    @Before
    public void onSetup(){
        toolCardParameters = new ToolCardParameters();
        toolCardParameters.draftPoolDiceIndex = -1;
        toolCardParameters.roundTrackDiceIndex = -1;
        toolCardParameters.panelDiceIndex = -1;
        toolCardParameters.secondPanelDiceIndex = -1;
        toolCardParameters.roundTrackRoundIndex = -1;
        toolCardParameters.panelCellIndex = -1;
        toolCardParameters.secondPanelCellIndex = -1;
        toolCardParameters.toolCardID = -1;
        toolCardParameters.actionSign = -1;
        toolCardParameters.diceValue = -1;
        toolCardParameters.twoDiceAction = true;
    }

    @Test
    public void sesft(){
        toolCardParameters.reset();
        assertNull(toolCardParameters.draftPoolDiceIndex);
        assertNull(toolCardParameters.roundTrackDiceIndex);
        assertNull(toolCardParameters.panelDiceIndex);
        assertNull(toolCardParameters.secondPanelDiceIndex);
        assertNull(toolCardParameters.roundTrackRoundIndex);
        assertNull(toolCardParameters.panelCellIndex);
        assertNull(toolCardParameters.secondPanelCellIndex);
        assertEquals(Integer.valueOf(0),toolCardParameters.toolCardID);
        assertNull(toolCardParameters.actionSign);
        assertNull(toolCardParameters.diceValue);
        assertNull(toolCardParameters.twoDiceAction);

    }
    @Test
    public void  nulld(){

    }
}