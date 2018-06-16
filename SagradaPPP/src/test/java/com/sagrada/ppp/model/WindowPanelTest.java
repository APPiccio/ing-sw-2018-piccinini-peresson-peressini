package com.sagrada.ppp.model;

import com.sagrada.ppp.utils.StaticValues;
import org.junit.*;

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
        assertTrue(tempWindowPanel.equals(windowPanel));
        assertNotEquals(tempWindowPanel.getCells().hashCode(), windowPanel.getCells().hashCode());
        assertEquals(tempWindowPanel.getCells(), windowPanel.getCells());

        for (int i = 0; i < StaticValues.NUMBER_OF_CELLS; i++) {
            assertNotEquals(tempWindowPanel.getCells().get(i).hashCode(), windowPanel.getCells().get(i).hashCode());
            assertEquals(tempWindowPanel.getCells().get(i), windowPanel.getCells().get(i));
        }
    }

    @Test
    public void getNumberOfPanels() {
    }


    @Test
    public void addDice() {
    }

    @Test
    public void addDice1() {
    }

    @Test
    public void diceOk() {
    }

    @Test
    public void noDiceNear() {
    }



    @Test
    public void equals() {
    }

    @Test
    public void toStringTest() {
    }

    @Test
    public void getLegalPosition() {
    }

    @Test
    public void removeDice() {
    }

    @Test
    public void diceOkWithRestriction() {
    }

    @Test
    public void getEmptyCells() {
    }

    @Test
    public void getPrivateScore() {
    }
}