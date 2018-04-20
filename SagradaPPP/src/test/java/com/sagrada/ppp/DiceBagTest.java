package com.sagrada.ppp;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class DiceBagTest {

    @Test
    public static void diceBagTest() {

        DiceBag diceBag = new DiceBag();

        //sizeTest
        assertEquals(90, diceBag.size());

        //extractRandomDiceTest
        DiceBag i = new DiceBag();
        DiceBag j = new DiceBag(i);

        assertEquals(i.getDiceBag(), j.getDiceBag());
        assertNotEquals(i.hashCode(), j.hashCode());

        ArrayList<Dice> k = i.extractDices(10);

        assertNotEquals(i.size(), j.size());
        assertTrue(j.getDiceBag().containsAll(i.getDiceBag()));
        assertTrue(j.getDiceBag().containsAll(k));
        assertEquals(i.size(), j.size() - k.size());

        //getDiceBagTest
        assertEquals(diceBag.size(), diceBag.getDiceBag().size());

        //extractDicesTest
        DiceBag x = new DiceBag();
        DiceBag y = new DiceBag(x);
        ArrayList<Dice> z = new ArrayList<>();

        z.addAll(x.extractDices(10));
        z.addAll(x.extractDices(20));
        z.addAll(x.extractDices(25));
        z.addAll(x.extractDices(35));

        assertEquals(0, x.getDiceBag().size());
        assertTrue(y.getDiceBag().containsAll(z));

        //numberOfColorTest
        for (Color color : Color.values()) {
            assertEquals(18, diceBag.numberOfColor(color));
        }

    }

}