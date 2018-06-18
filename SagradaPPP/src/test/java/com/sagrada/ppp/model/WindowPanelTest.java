package com.sagrada.ppp.model;

import com.sagrada.ppp.utils.StaticValues;
import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class WindowPanelTest {

    private WindowPanel windowPanel;

    @Before
    public void setUp() {
        windowPanel = new WindowPanel(1, 1);
    }

    @Test(expected = NullPointerException.class)
    public void nullWindowPanel() {
        new WindowPanel(null);
    }

    @Test
    public void getPanelName() {
        assertEquals("Kaleidoscopic Dream", windowPanel.getPanelName());
    }

    @Test
    public void getFavorTokens() {
        assertEquals(4, windowPanel.getFavorTokens());
    }

    @Test
    public void getCardID() {
        assertEquals(1, windowPanel.getCardID());
    }

    @Test
    public void getCell() {
        Cell cell = windowPanel.getCell(-1);
        assertNull(cell);

        cell = windowPanel.getCell(42);
        assertNull(cell);

        cell = windowPanel.getCell(-1, -1);
        assertNull(cell);

        cell = windowPanel.getCell(42, 0);
        assertNull(cell);

        cell = windowPanel.getCell(0, 42);
        assertNull(cell);

        cell = windowPanel.getCell(0);
        assertEquals(Color.YELLOW, cell.getColor());
        assertNull(cell.getValue());
        cell = windowPanel.getCell(4);
        assertNull(cell.getColor());
        assertEquals(Integer.valueOf(1), cell.getValue());

        cell = windowPanel.getCell(2, 2);
        assertEquals(Color.RED, cell.getColor());
        assertNull(cell.getValue());
        cell = windowPanel.getCell(3, 0);
        assertNull(cell.getColor());
        assertEquals(Integer.valueOf(2), cell.getValue());

        cell = windowPanel.getCell(3);
        assertNull(cell.getColor());
        assertNull(cell.getValue());

        cell = windowPanel.getCell(3, 2);
        assertNull(cell.getColor());
        assertNull(cell.getValue());
    }

    @Test
    public void getCells() {
        WindowPanel tempWindowPanel = new WindowPanel(windowPanel);

        assertNotEquals(tempWindowPanel.hashCode(), windowPanel.hashCode());
        assertEquals(tempWindowPanel, windowPanel);
        assertNotEquals(tempWindowPanel.getCells().hashCode(), windowPanel.getCells().hashCode());
        assertEquals(tempWindowPanel.getCells(), windowPanel.getCells());

        for (int i = 0; i < StaticValues.NUMBER_OF_CELLS; i++) {
            assertNotEquals(tempWindowPanel.getCells().get(i).hashCode(), windowPanel.getCells().get(i).hashCode());
            assertEquals(tempWindowPanel.getCells().get(i), windowPanel.getCells().get(i));
        }
    }

    @Test
    public void addDiceTest() {
        System.out.println("\nStart testing 'addDice' method\n");

        Dice dice = new Dice(Color.GREEN, 6);

        //Trying to place a dice in a empty windowPanel but not in a border position
        assertFalse(windowPanel.addDice(6, dice));
        assertFalse(windowPanel.addDice(13, dice));

        //Trying to place a dice in a empty windowPanel but not in a border position,
        //with the ignorePosition flag set to true
        assertFalse(windowPanel.addDice(6, dice, false, false, true));

        //Placing correctly a dice in an empty, color-restricted, border-positioned cell of an empty windowPanel
        assertTrue(windowPanel.addDice(5, dice));
        windowPanel.removeDice(5);

        //Placing correctly a dice in an empty, value-restricted, border-positioned cell of an empty windowPanel
        dice = new Dice(4);
        assertTrue(windowPanel.addDice(9, dice));
        windowPanel.removeDice(9);

        //Placing correctly a dice in an empty, restriction-less, border-positioned cell of an empty windowPanel
        dice = new Dice(Color.GREEN, 6);
        assertTrue(windowPanel.addDice(2, dice));
        assertEquals(dice, windowPanel.getCells().get(2).getDiceOn());

        //Placing correctly a dice in an empty, restriction-less cell of a not empty windowPanel
        //ignoring positioning restrictions
        assertTrue(windowPanel.addDice(17, new Dice(Color.PURPLE, 3),
                false, false, true));
        windowPanel.removeDice(17);

        //Trying to place a dice in an already occupied cell
        assertFalse(windowPanel.addDice(2, new Dice()));

        //Trying to place a dice in a not empty windowPanel, in a restriction-less cell with no dices near
        assertFalse(windowPanel.addDice(11, new Dice()));
        assertFalse(windowPanel.addDice(17, new Dice()));

        //Trying to place a dice near an occupied cell, in a restriction-less position,
        //violating color-positioning restriction
        assertFalse(windowPanel.addDice(3, new Dice(Color.GREEN, 5)));

        //Trying to place a dice near an occupied cell, in a restriction-less position,
        //violating value-positioning restriction
        assertFalse(windowPanel.addDice(3, new Dice(Color.RED, 6)));

        //Trying to place a dice near an occupied cell, in a restriction-less position,
        //violating color and value positioning restrictions
        assertFalse(windowPanel.addDice(3, dice));

        //Placing correctly a dice orthogonally near an occupied cell, in a restriction-less position
        assertTrue(windowPanel.addDice(2 + 1, new Dice(Color.PURPLE, 3)));
        windowPanel.removeDice(2 + 1);

        //Placing correctly a dice diagonally near an occupied cell, in a restriction-less position
        assertTrue(windowPanel.addDice(2 + 5 + 1, new Dice(Color.PURPLE, 3)));
        windowPanel.removeDice(2 + 5 + 1);

        //Trying to place a dice respecting positioning restrictions but violating cell-color restriction
        assertFalse(windowPanel.addDice(2 - 1, new Dice(Color.RED, 4)));

        //Trying to place a dice respecting positioning restrictions but violating cell-value restriction
        assertFalse(windowPanel.addDice(2 + 5, new Dice(Color.RED, 4)));

        //Placing correctly a dice respecting positioning restrictions and ignoring cell-color restriction
        assertTrue(windowPanel.addDice(2 - 1, new Dice(Color.RED, 4),
                true, false, false));
        windowPanel.removeDice(2 - 1);

        //Trying to place a dice respecting positioning restrictions, ignoring cell-color restriction but
        //on a cell with value restriction
        assertFalse(windowPanel.addDice(2 + 5, new Dice(Color.RED, 4),
                true, false, true));

        //Placing correctly a dice respecting positioning restrictions, ignoring cell-color restriction but
        //on a cell with value restriction
        assertTrue(windowPanel.addDice(2 + 5, new Dice(Color.RED, 5),
                true, false, true));
        windowPanel.removeDice(2 + 5);

        //Placing correctly a dice respecting positioning restrictions and ignoring cell-value restriction
        assertTrue(windowPanel.addDice(2 + 5, new Dice(Color.RED, 4),
                false, true, true));
        windowPanel.removeDice(2 + 5);

        //Trying to place a dice respecting positioning restrictions, ignoring cell-value restriction but
        //on a cell with color restriction
        assertFalse(windowPanel.addDice(2 - 1, new Dice(Color.RED, 4),
                false, true, false));

        System.out.println("\nEnd testing 'addDice' method");
    }

    @Test
    public void getLegalPosition() {
        System.out.println("Start testing 'getLegalPosition' method\n");

        ArrayList<Integer> legalPositions = windowPanel.getLegalPosition(new Dice(Color.GREEN, 6));

        assertEquals(6, legalPositions.size());
        assertEquals(Integer.valueOf(2), legalPositions.get(0));
        assertEquals(Integer.valueOf(3), legalPositions.get(1));
        assertEquals(Integer.valueOf(5), legalPositions.get(2));
        assertEquals(Integer.valueOf(14), legalPositions.get(3));
        assertEquals(Integer.valueOf(16), legalPositions.get(4));
        assertEquals(Integer.valueOf(17), legalPositions.get(5));

        windowPanel.addDice(2, new Dice(Color.GREEN, 6));

        legalPositions = windowPanel.getLegalPosition(new Dice(Color.GREEN, 6));

        assertEquals(2, legalPositions.size());
        assertEquals(Integer.valueOf(6), legalPositions.get(0));
        assertEquals(Integer.valueOf(8), legalPositions.get(1));

        System.out.println("\nEnd testing 'getLegalPosition' method");
    }

    @Test
    public void removeDice() {
        Dice dice = new Dice(Color.GREEN, 6);
        windowPanel.addDice(2, dice);

        assertEquals(19, windowPanel.getEmptyCells());

        Dice tempDice = windowPanel.removeDice(2);

        assertEquals(tempDice, dice);
        assertEquals(20, windowPanel.getEmptyCells());
    }

    @Test
    public void getEmptyCells() {
        assertEquals(20, windowPanel.getEmptyCells());

        windowPanel.addDice(2, new Dice(Color.PURPLE, 4));
        windowPanel.addDice(3, new Dice(Color.GREEN, 3));

        assertNotEquals(19, windowPanel.getEmptyCells());
        assertEquals(18, windowPanel.getEmptyCells());
    }

    @Test
    public void getPrivateScore() {
        assertEquals(0, windowPanel.getPrivateScore(Color.getRandomColor()));

        windowPanel.addDice(2, new Dice(Color.GREEN, 6));

        assertEquals(6, windowPanel.getPrivateScore(Color.GREEN));

        windowPanel.addDice(2 + 5 + 1, new Dice(Color.GREEN, 5));
        windowPanel.addDice(2 + 5 + 1 + 5 + 1, new Dice(Color.GREEN, 3));

        assertEquals(6 + 5 + 3, windowPanel.getPrivateScore(Color.GREEN));
    }

    @Test
    public void equals() {
        WindowPanel tempWindowPanel = new WindowPanel(windowPanel);

        assertNotEquals(tempWindowPanel.hashCode(), windowPanel.hashCode());
        assertEquals(tempWindowPanel, windowPanel);

        assertNotEquals(windowPanel, null);
        assertNotEquals(tempWindowPanel, null);

        assertEquals(windowPanel, windowPanel);
        assertEquals(tempWindowPanel, tempWindowPanel);

        Dice dice = new Dice();

        assertNotEquals(windowPanel, dice);
        assertNotEquals(tempWindowPanel, dice);

        tempWindowPanel = new WindowPanel(1, 0);

        assertNotEquals(windowPanel, tempWindowPanel);
    }

}