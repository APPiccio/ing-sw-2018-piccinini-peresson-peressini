package com.sagrada.ppp.model;

import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class DiceBagTest {

    @Test
    public void DiceBag() {
        DiceBag diceBag = new DiceBag();

        for (Color color : Color.values()) {
            assertEquals(18, numberOfColor(diceBag, color));
        }
    }

    @Test
    public void size() {
        DiceBag diceBag = new DiceBag();

        assertEquals(90, diceBag.size());
    }

    @Test
    public void extractRandomDice() {
        DiceBag diceBag = new DiceBag();
        DiceBag oldDiceBag = new DiceBag(diceBag);
        diceBag.extractRandomDice();

        assertEquals(oldDiceBag.size(), diceBag.size() + 1);
    }

    @Test
    public void getDiceBag() {
        DiceBag diceBag = new DiceBag();
        ArrayList<Dice> dices1 = diceBag.getDiceBag();
        ArrayList<Dice> dices2 = diceBag.getDiceBag();

        assertEquals(dices1, dices2);
        assertNotEquals(dices1.hashCode(), dices2.hashCode());
        int size = dices1.size();
        for (int i = 0; i < size; i++) {
            assertEquals(dices1.get(i), dices2.get(i));
            assertNotEquals(dices1.get(i).hashCode(), dices2.get(i).hashCode());
        }
    }

    @Test
    public void extractDices() {
        DiceBag diceBag = new DiceBag();
        DiceBag oldDiceBag = new DiceBag(diceBag);

        assertEquals(diceBag, oldDiceBag);

        ArrayList<Dice> dices = diceBag.extractDices(9);

        assertEquals(9, dices.size());
        assertEquals(81, diceBag.size());
        assertEquals(oldDiceBag.size() - dices.size(), diceBag.size());
        for (Dice dice : dices) {
            assertTrue(oldDiceBag.getDiceBag().contains(dice));
        }
    }

    @Test
    public void addDice() {
        DiceBag diceBag = new DiceBag();
        DiceBag oldDiceBag = new DiceBag(diceBag);
        Dice dice = new Dice(Color.YELLOW);

        assertEquals(18, numberOfColor(diceBag, Color.YELLOW));

        diceBag.addDice(dice);

        assertEquals(18, numberOfColor(oldDiceBag, Color.YELLOW));
        assertEquals(19, numberOfColor(diceBag, Color.YELLOW));
        assertEquals(diceBag.size(), oldDiceBag.size() + 1);
        assertEquals(dice.getColor(), diceBag.getDiceBag().get(diceBag.size() - 1).getColor());
        assertNotEquals(dice.hashCode(), diceBag.getDiceBag().get(diceBag.size() - 1).hashCode());
    }

    @Test
    public void equals() {
        DiceBag diceBag = new DiceBag();
        DiceBag oldDiceBag = new DiceBag(diceBag);

        assertNotEquals(diceBag.hashCode(), oldDiceBag.hashCode());
        assertTrue(oldDiceBag.equals(diceBag));

        diceBag.addDice(new Dice(Color.YELLOW));

        assertFalse(oldDiceBag.equals(diceBag));
    }

    @Test(expected = IllegalArgumentException.class)
    public void extractDicesException() {
        DiceBag diceBag = new DiceBag();
        diceBag.extractDices(777);
    }

    private int numberOfColor(DiceBag diceBag, Color color) {
        return (int) diceBag.getDiceBag().stream().filter(x -> x.getColor() == color).count();
    }

}