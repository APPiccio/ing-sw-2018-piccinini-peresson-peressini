package com.sagrada.ppp;

import org.junit.Test;
import static org.junit.Assert.*;

public class DiceTest {
    public DiceTest() {
        diceTest();
    }

    @Test
    public void diceTest() {

        Dice x = new Dice(Color.BLUE, 5);
        Dice y = new Dice(Color.BLUE, 6);
        Dice z = new Dice(Color.RED, 5);
        Dice w = new Dice(Color.RED, 5);
        Dice s = new Dice(Color.YELLOW, 1);
        Dice t = new Dice(s);

        //testing if the repo can't be returned
        assertNotEquals(s.hashCode(), t.hashCode());

        //isSimilarTest
        assertTrue(x.isSimilar(y));
        assertTrue(x.isSimilar(z));
        assertTrue(z.isSimilar(w));
        assertFalse(x.isSimilar(s));

        //setValueTest
        x.setValue(1);
        assertEquals(1, x.getValue());
        y.setValue(2);
        assertNotEquals(6, y.getValue());

    }

}