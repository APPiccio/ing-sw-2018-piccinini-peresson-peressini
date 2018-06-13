package com.sagrada.ppp.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class CellTest {

    //TODO da riorganizzare in stile altre classi di test nella cartella model

    @Test
    public void testCell() {
        Dice dice = new Dice();

        Cell blankCell = new Cell();
        assertNull(blankCell.getColor());
        assertNull(blankCell.getValue());
        assertNull(blankCell.getDiceOn());
        assertFalse(blankCell.hasColorRestriction());
        assertFalse(blankCell.hasValueRestriction());

        Cell numberedCell = new Cell(3);
        assertEquals(Integer.valueOf(3), numberedCell.getValue());
        assertNotEquals(Integer.valueOf(5), numberedCell.getValue());
        assertFalse(numberedCell.hasColorRestriction());
        assertTrue(numberedCell.hasValueRestriction());
        assertFalse(numberedCell.hasDiceOn());
        assertNull(numberedCell.getDiceOn());

        Cell coloredCell = new Cell(Color.RED);
        assertEquals(Color.RED, coloredCell.getColor());
        assertNotEquals(Color.GREEN, coloredCell.getColor());
        assertTrue(coloredCell.hasColorRestriction());
        assertFalse(coloredCell.hasValueRestriction());
        assertFalse(coloredCell.hasDiceOn());
        assertNull(coloredCell.getDiceOn());

        numberedCell.setDiceOn(dice);

        Cell cell = new Cell(numberedCell);
        assertNotEquals(cell.hashCode(), numberedCell.hashCode());
        assertTrue(cell.hasDiceOn());
        assertEquals(Integer.valueOf(3), cell.getValue());
        assertEquals(dice, cell.getDiceOn());

        cell = new Cell(coloredCell);
        assertNotEquals(cell.hashCode(), coloredCell.hashCode());
        assertFalse(cell.hasDiceOn());
        assertEquals(Color.RED, cell.getColor());
        assertNull(cell.getDiceOn());

        assertFalse(coloredCell.equals(numberedCell));
        assertFalse(numberedCell.equals(coloredCell));

        Dice testDice1 = new Dice(Color.GREEN, 6);
        Dice testDice2 = new Dice(Color.RED, 1);
        numberedCell = new Cell(coloredCell);
        numberedCell.setDiceOn(testDice1);
        coloredCell.setDiceOn(testDice2);

        assertFalse(numberedCell.equals(coloredCell));

        coloredCell.setDiceOn(testDice1);

        assertTrue(numberedCell.equals(coloredCell));
    }

    @Test
    public void toStringTest() {
        Cell numberedCell = new Cell(3);
        numberedCell.setDiceOn(new Dice());

        assertEquals("Color = " + numberedCell.getColor() + "\t\t" + "Value = " +
                        numberedCell.getValue() + "\t\t" + "Dice = " + numberedCell.getDiceOn().toString() + "\n",
                numberedCell.toString());

        Cell coloredCell = new Cell(Color.RED);

        assertEquals("Color = " + coloredCell.getColor() + "\t\t" + "Value = " +
                coloredCell.getValue() + "\t\t\n", coloredCell.toString());
    }

}