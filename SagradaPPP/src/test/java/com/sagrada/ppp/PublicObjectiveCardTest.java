package com.sagrada.ppp;

import com.sagrada.ppp.Cards.*;
import org.junit.Test;

import java.io.FileNotFoundException;

import static junit.framework.Assert.assertEquals;

public class PublicObjectiveCardTest {

    @Test
    public static void card1() throws FileNotFoundException {

        PublicObjectiveCard card = new PublicObjectiveCard1();

        WindowPanel panel = TestPanels.panel200();
        assertEquals(24, card.getScore(panel));
    }

    /**
     *
     * This getScore() method doesn't check any dice value
     */
    @Test
    public static void card9() throws FileNotFoundException {

        WindowPanel panel = new WindowPanel(0, 0);

        boolean result;

        PublicObjectiveCard card = new PublicObjectiveCard9();

        result = panel.addDiceOnCellWithIndex(0,new Dice(Color.RED, 1));
        result = panel.addDiceOnCellWithIndex(1,new Dice(Color.GREEN, 2));
        result = panel.addDiceOnCellWithIndex(2,new Dice(Color.BLUE, 1));
        result = panel.addDiceOnCellWithIndex(3,new Dice(Color.GREEN, 2));
        result = panel.addDiceOnCellWithIndex(4,new Dice(Color.PURPLE, 1));

        result = panel.addDiceOnCellWithIndex(5,new Dice(Color.YELLOW, 3));
        result = panel.addDiceOnCellWithIndex(6,new Dice(Color.BLUE, 4));
        result = panel.addDiceOnCellWithIndex(7,new Dice(Color.GREEN, 3));
        result = panel.addDiceOnCellWithIndex(8,new Dice(Color.PURPLE, 4));
        result = panel.addDiceOnCellWithIndex(9,new Dice(Color.RED, 3));

        result = panel.addDiceOnCellWithIndex(10,new Dice(Color.PURPLE, 1));
        result = panel.addDiceOnCellWithIndex(11,new Dice(Color.YELLOW, 2));
        //Cell without dice
        result = panel.addDiceOnCellWithIndex(13,new Dice(Color.GREEN, 2));
        result = panel.addDiceOnCellWithIndex(14,new Dice(Color.BLUE, 1));

        result = panel.addDiceOnCellWithIndex(15,new Dice(Color.RED, 3));
        result = panel.addDiceOnCellWithIndex(16,new Dice(Color.BLUE, 4));
        result = panel.addDiceOnCellWithIndex(17,new Dice(Color.YELLOW, 3));
        result = panel.addDiceOnCellWithIndex(18,new Dice(Color.RED, 4));
        result = panel.addDiceOnCellWithIndex(19,new Dice(Color.YELLOW, 3));

        assertEquals(11, card.getScore(panel));

    }

    @Test
    public static void card10() throws FileNotFoundException {

        WindowPanel panel = new WindowPanel(0, StaticValues.BACK_SIDE);



        PublicObjectiveCard card = new PublicObjectiveCard10();


        panel.addDiceOnCellWithIndex(0,new Dice(Color.RED, 1));
        panel.addDiceOnCellWithIndex(1,new Dice(Color.GREEN, 2));
        panel.addDiceOnCellWithIndex(2,new Dice(Color.BLUE, 1));
        panel.addDiceOnCellWithIndex(3,new Dice(Color.GREEN, 2));
        panel.addDiceOnCellWithIndex(4,new Dice(Color.PURPLE, 1));

        panel.addDiceOnCellWithIndex(5,new Dice(Color.YELLOW, 3));
        panel.addDiceOnCellWithIndex(6,new Dice(Color.BLUE, 4));
        panel.addDiceOnCellWithIndex(7,new Dice(Color.GREEN, 3));
        panel.addDiceOnCellWithIndex(8,new Dice(Color.PURPLE, 4));
        panel.addDiceOnCellWithIndex(9,new Dice(Color.RED, 3));

        panel.addDiceOnCellWithIndex(10,new Dice(Color.PURPLE, 1));
        panel.addDiceOnCellWithIndex(11,new Dice(Color.YELLOW, 2));
        //Cell without dice
        panel.addDiceOnCellWithIndex(13,new Dice(Color.GREEN, 2));
        panel.addDiceOnCellWithIndex(14,new Dice(Color.BLUE, 1));

        panel.addDiceOnCellWithIndex(15,new Dice(Color.RED, 3));
        panel.addDiceOnCellWithIndex(16,new Dice(Color.BLUE, 4));
        panel.addDiceOnCellWithIndex(17,new Dice(Color.YELLOW, 3));
        panel.addDiceOnCellWithIndex(18,new Dice(Color.RED, 4));
        panel.addDiceOnCellWithIndex(19,new Dice(Color.YELLOW, 3));

        assertEquals(3*4, card.getScore(panel));

    }

    @Test
    public static void card8() throws FileNotFoundException {

        WindowPanel panel = new WindowPanel(0, StaticValues.BACK_SIDE);



        PublicObjectiveCard card = new PublicObjectiveCard8();

        panel.addDiceOnCellWithIndex(0,new Dice(Color.RED, 1));
        panel.addDiceOnCellWithIndex(1,new Dice(Color.GREEN, 2));
        panel.addDiceOnCellWithIndex(2,new Dice(Color.BLUE, 1));
        panel.addDiceOnCellWithIndex(3,new Dice(Color.GREEN, 2));
        panel.addDiceOnCellWithIndex(4,new Dice(Color.PURPLE, 1));

        panel.addDiceOnCellWithIndex(5,new Dice(Color.YELLOW, 3));
        panel.addDiceOnCellWithIndex(6,new Dice(Color.BLUE, 4));
        panel.addDiceOnCellWithIndex(7,new Dice(Color.GREEN, 3));
        panel.addDiceOnCellWithIndex(8,new Dice(Color.PURPLE, 4));
        panel.addDiceOnCellWithIndex(9,new Dice(Color.RED, 3));

        panel.addDiceOnCellWithIndex(10,new Dice(Color.PURPLE, 1));
        panel.addDiceOnCellWithIndex(11,new Dice(Color.YELLOW, 2));
        //Cell without dice
        panel.addDiceOnCellWithIndex(13,new Dice(Color.GREEN, 2));
        panel.addDiceOnCellWithIndex(14,new Dice(Color.BLUE, 1));

        panel.addDiceOnCellWithIndex(15,new Dice(Color.RED, 3));
        panel.addDiceOnCellWithIndex(16,new Dice(Color.BLUE, 4));
        panel.addDiceOnCellWithIndex(17,new Dice(Color.YELLOW, 3));
        panel.addDiceOnCellWithIndex(18,new Dice(Color.RED, 4));
        panel.addDiceOnCellWithIndex(19,new Dice(Color.YELLOW, 3));

        assertEquals(0, card.getScore(panel));

        panel = new WindowPanel(0,0);
        panel.addDiceOnCellWithIndex(0,new Dice(Color.RED, 1));
        panel.addDiceOnCellWithIndex(1,new Dice(Color.GREEN, 2));
        panel.addDiceOnCellWithIndex(2,new Dice(Color.BLUE, 1));
        panel.addDiceOnCellWithIndex(3,new Dice(Color.GREEN, 2));
        panel.addDiceOnCellWithIndex(4,new Dice(Color.PURPLE, 6));

        panel.addDiceOnCellWithIndex(5,new Dice(Color.YELLOW, 3));
        panel.addDiceOnCellWithIndex(6,new Dice(Color.BLUE, 4));
        panel.addDiceOnCellWithIndex(7,new Dice(Color.GREEN, 3));
        panel.addDiceOnCellWithIndex(8,new Dice(Color.PURPLE, 4));
        panel.addDiceOnCellWithIndex(9,new Dice(Color.RED, 3));

        panel.addDiceOnCellWithIndex(10,new Dice(Color.PURPLE, 5));
        panel.addDiceOnCellWithIndex(11,new Dice(Color.YELLOW, 6));
        //Cell without dice
        panel.addDiceOnCellWithIndex(13,new Dice(Color.GREEN, 5));
        panel.addDiceOnCellWithIndex(14,new Dice(Color.BLUE, 6));

        panel.addDiceOnCellWithIndex(15,new Dice(Color.RED, 1));
        panel.addDiceOnCellWithIndex(16,new Dice(Color.BLUE, 2));
        panel.addDiceOnCellWithIndex(17,new Dice(Color.YELLOW, 3));
        panel.addDiceOnCellWithIndex(18,new Dice(Color.RED, 4));
        panel.addDiceOnCellWithIndex(19,new Dice(Color.YELLOW, 5));

        assertEquals(3*5, card.getScore(panel));

    }
}
