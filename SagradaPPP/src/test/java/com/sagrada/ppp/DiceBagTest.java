package com.sagrada.ppp;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class DiceBagTest {

    @Test
    public static void sizeTest() {
        DiceBag diceBag = new DiceBag();
        assertEquals(90, diceBag.size());
    }

    @Test
    public static void numberOfColorTest() {
        DiceBag diceBag = new DiceBag();
        for (Color color : Color.values()) {
            assertEquals(18, diceBag.numberOfColor(color));
        }
    }

}