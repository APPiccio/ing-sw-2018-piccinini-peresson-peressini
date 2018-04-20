package com.sagrada.ppp;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Random;

public class DiceBagTest {

    @Test
    public static void diceBagTest() {

        DiceBag diceBag = new DiceBag();
        ArrayList<Dice> dices = new ArrayList<>();
        ArrayList<Dice> newDiceBag;
        int random = new Random().nextInt(90);

        //sizeTest
        assertEquals(90, diceBag.size());

        //numberOfColorTest
        for (Color color : Color.values()) {
            assertEquals(18, diceBag.numberOfColor(color));
        }

        //getDiceBagTest
        assertEquals(diceBag.size(), diceBag.getDiceBag().size());

        //removeDicesTest
        for (int i = 0; i < random; i++) {
            dices.add(diceBag.getRandomDice());
        }
        assertTrue(diceBag.getDiceBag().containsAll(dices));
    }

}