package com.sagrada.ppp;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CellTest {

    @Test
    public static void cellValueTest() {

        Dice dice = new Dice();
        Cell blankCell = new Cell();
        Cell numberedCell = new Cell(dice.getValue());
        Cell coloredCell = new Cell(dice.getColor());

        numberedCell.setDiceOn(dice);
        coloredCell.setDiceOn(dice);

        assertEquals(Boolean.FALSE, blankCell.hasColorRestriction());
        assertEquals(Boolean.FALSE, blankCell.hasValueRestriction());
        assertEquals(Boolean.FALSE, blankCell.hasDiceOn());

        assertEquals(Boolean.FALSE, numberedCell.hasColorRestriction());
        assertEquals(Boolean.TRUE, numberedCell.hasValueRestriction());
        assertEquals(Boolean.TRUE, numberedCell.hasDiceOn());

        assertEquals(Boolean.TRUE, coloredCell.hasColorRestriction());
        assertEquals(Boolean.FALSE, coloredCell.hasValueRestriction());
        assertEquals(Boolean.TRUE, coloredCell.hasDiceOn());


    }

}
