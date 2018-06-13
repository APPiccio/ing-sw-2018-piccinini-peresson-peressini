package com.sagrada.ppp.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class DiceBagTest {

    @Test
    public void testAll() {
    }

    @Test
    public void DiceBag() {
        DiceBag diceBag = new DiceBag();

        for (Color color : Color.values()) {
            assertEquals(18, diceBag.numberOfColor(color));
        }
    }

    @Test
    public void size() {
        DiceBag diceBag = new DiceBag();

        assertEquals(90, diceBag.size());
    }

    @Test
    public void extractRandomDice() {
    }

    @Test
    public void getDiceBag() {
    }

    @Test
    public void extractDices() {
    }

    @Test
    public void addDice() {
    }

    @Test
    public void equals() {
    }

}