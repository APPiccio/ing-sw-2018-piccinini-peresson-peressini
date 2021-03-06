package com.sagrada.ppp.model;

import org.junit.*;

import static org.junit.Assert.*;

public class CellTest {

    private Dice dice;
    private Cell blankCell;
    private Cell numberedCell;
    private Cell coloredCell;

    @Before
    public void setUp() {
        dice = new Dice(Color.PURPLE, 4);
        blankCell = new Cell();
        numberedCell = new Cell(3);
        coloredCell = new Cell(Color.RED);
    }

    @Test
    public void hasColorRestriction() {
        assertFalse(blankCell.hasColorRestriction());
        assertFalse(numberedCell.hasColorRestriction());
        assertTrue(coloredCell.hasColorRestriction());
    }

    @Test
    public void hasValueRestriction() {
        assertFalse(blankCell.hasValueRestriction());
        assertTrue(numberedCell.hasValueRestriction());
        assertFalse(coloredCell.hasValueRestriction());
    }

    @Test
    public void getColor() {
        assertNull(blankCell.getColor());
        assertNull(numberedCell.getColor());
        assertEquals(Color.RED, coloredCell.getColor());
        assertNotEquals(Color.GREEN, coloredCell.getColor());
    }

    @Test
    public void getValue() {
        assertNull(blankCell.getValue());
        assertNull(coloredCell.getValue());
        assertEquals(Integer.valueOf(3), numberedCell.getValue());
        assertNotEquals(Integer.valueOf(5), numberedCell.getValue());
    }

    @Test
    public void getDiceOn_setDiceOn_hasDiceOn() {
        assertFalse(blankCell.hasDiceOn());
        assertFalse(coloredCell.hasDiceOn());
        assertFalse(numberedCell.hasDiceOn());

        assertNull(blankCell.getDiceOn());
        assertNull(coloredCell.getDiceOn());
        assertNull(numberedCell.getDiceOn());

        numberedCell.setDiceOn(dice);

        assertTrue(numberedCell.hasDiceOn());
        assertNotNull(numberedCell.getDiceOn());
        assertEquals(Color.PURPLE, numberedCell.getDiceOn().getColor());
        assertEquals(4, numberedCell.getDiceOn().getValue());

        Cell cell = new Cell(coloredCell);

        assertNotEquals(coloredCell.hashCode(), cell.hashCode());
        assertEquals(Color.RED, cell.getColor());
        assertNull(cell.getValue());

        cell = new Cell(numberedCell);
        assertNotEquals(numberedCell.hashCode(), cell.hashCode());
        assertEquals(Integer.valueOf(3), cell.getValue());
        assertNull(cell.getColor());

        assertTrue(cell.hasDiceOn());
        assertNotNull(cell.getDiceOn());
        assertEquals(Color.PURPLE, cell.getDiceOn().getColor());
        assertEquals(4, cell.getDiceOn().getValue());
    }

    @Test
    public void equals() {
        assertNotEquals(coloredCell, numberedCell);
        assertNotEquals(numberedCell, coloredCell);
        assertEquals(coloredCell, coloredCell);
        assertEquals(numberedCell, numberedCell);

        Cell cell = new Cell(coloredCell);
        assertEquals(coloredCell, cell);

        Dice testDice1 = new Dice(Color.GREEN, 6);
        Dice testDice2 = new Dice(Color.RED, 1);

        numberedCell = new Cell(coloredCell);
        assertEquals(numberedCell, coloredCell);

        numberedCell.setDiceOn(testDice1);

        assertNotEquals(numberedCell, coloredCell);

        coloredCell.setDiceOn(testDice2);

        assertNotEquals(numberedCell, coloredCell);

        coloredCell.setDiceOn(testDice1);

        assertEquals(numberedCell, coloredCell);

        assertNotEquals(coloredCell, null);
        assertNotEquals(numberedCell, null);
        assertNotEquals(coloredCell, testDice1);
        assertNotEquals(numberedCell, testDice2);

        coloredCell = new Cell(Color.RED);
        numberedCell = new Cell(6);

        coloredCell.setDiceOn(testDice1);
        numberedCell.setDiceOn(testDice1);
        assertNotEquals(coloredCell, numberedCell);

        Cell tempColoredCell = new Cell(Color.GREEN);
        tempColoredCell.setDiceOn(testDice1);
        assertNotEquals(coloredCell, tempColoredCell);

        Cell tempNumberedCell = new Cell(2);
        tempNumberedCell.setDiceOn(testDice1);
        assertNotEquals(numberedCell, tempNumberedCell);

        tempColoredCell = new Cell(coloredCell);
        assertEquals(tempColoredCell, coloredCell);

        tempNumberedCell = new Cell(numberedCell);
        assertEquals(tempNumberedCell, numberedCell);
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