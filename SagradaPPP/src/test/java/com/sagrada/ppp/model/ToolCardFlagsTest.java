package com.sagrada.ppp.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ToolCardFlagsTest {
    ToolCardFlags toolCardFlags;
    @Before
    public void setUp(){
        toolCardFlags = new ToolCardFlags();
        toolCardFlags.isSecondPanelCellRequired = true;
        toolCardFlags.isSecondPanelDiceRequired = true;
        toolCardFlags.isPanelDiceRequired = true;
        toolCardFlags.isPanelCellRequired = true;
        toolCardFlags.isDraftPoolDiceRequired = true;
        toolCardFlags.isRoundTrackDiceRequired = true;
        toolCardFlags.isActionSignRequired = true;
        toolCardFlags.isReRolledDiceActionRequired = true;
        toolCardFlags.isTwoDiceActionRequired = true;
        toolCardFlags.isDiceValueRequired = true;
        toolCardFlags.colorDiceValueRequired = Color.RED;
        toolCardFlags.reRolledDice = new Dice();

    }

    @Test
    public void reset() {

        toolCardFlags.reset();
        assertFalse(toolCardFlags.isSecondPanelCellRequired);
        assertFalse(toolCardFlags.isSecondPanelDiceRequired);
        assertFalse(toolCardFlags.isPanelDiceRequired);
        assertFalse(toolCardFlags.isPanelCellRequired);
        assertFalse(toolCardFlags.isDraftPoolDiceRequired);
        assertFalse(toolCardFlags.isRoundTrackDiceRequired);
        assertFalse(toolCardFlags.isActionSignRequired);
        assertFalse(toolCardFlags.isReRolledDiceActionRequired);
        assertFalse(toolCardFlags.isTwoDiceActionRequired);
        assertFalse(toolCardFlags.isDiceValueRequired);
        assertNull(toolCardFlags.reRolledDice);
        assertNull(toolCardFlags.colorDiceValueRequired);


    }
}