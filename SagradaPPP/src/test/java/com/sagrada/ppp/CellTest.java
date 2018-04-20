package com.sagrada.ppp;

import org.junit.Test;
import static org.junit.Assert.*;

public class CellTest {

    @Test
    public static void cellTest() {

        Dice dice = new Dice(); //Dice with random color and random value
        Cell blankCell = new Cell();
        Cell coloredCell = new Cell(dice.getColor());
        Cell numberedCell = new Cell(dice.getValue());
        coloredCell.setDiceOn(dice);
        numberedCell.setDiceOn(dice);

        //hasColorRestrictionTest
        assertFalse(blankCell.hasColorRestriction());
        assertFalse(numberedCell.hasColorRestriction());
        assertTrue(coloredCell.hasColorRestriction());

        //hasValueRestrictionTest
        assertFalse(blankCell.hasValueRestriction());
        assertFalse(coloredCell.hasValueRestriction());
        assertTrue(numberedCell.hasValueRestriction());

        //hasDiceOnTest
        assertFalse(blankCell.hasDiceOn());
        assertTrue(coloredCell.hasDiceOn());
        assertTrue(numberedCell.hasDiceOn());

        //getDiceOnTest - setDiceOnTest
        assertNull(blankCell.getDiceOn());
        assertEquals((coloredCell.getDiceOn()), dice);
        assertEquals((numberedCell.getDiceOn()), dice);
        assertNotEquals((coloredCell.getDiceOn()).hashCode(), dice.hashCode());
        assertNotEquals((numberedCell.getDiceOn()).hashCode(), dice.hashCode());

    }

}