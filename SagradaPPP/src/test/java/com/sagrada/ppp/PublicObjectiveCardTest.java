package com.sagrada.ppp;

import com.sagrada.ppp.Cards.*;
import org.junit.Test;
import java.io.FileNotFoundException;
import static junit.framework.Assert.assertEquals;

public class PublicObjectiveCardTest {

    @Test
    public static void card1() throws FileNotFoundException {

        PublicObjectiveCard card = new PublicObjectiveCard1();
        assertEquals(0, card.getScore(new WindowPanel(0,0)));
        assertEquals(24, card.getScore(TestPanels.panel_210()));
        assertEquals(12, card.getScore(TestPanels.panel_211()));
        assertEquals(0, card.getScore(TestPanels.panel_212()));

    }

    public static void card2() throws FileNotFoundException{

        PublicObjectiveCard card = new PublicObjectiveCard2();
        assertEquals(0, card.getScore(new WindowPanel(0,0)));
        assertEquals(25, card.getScore(TestPanels.panel_220()));
        assertEquals(15, card.getScore(TestPanels.panel_221()));
        assertEquals(0, card.getScore(TestPanels.panel_222()));

    }



    public static void card3() throws FileNotFoundException {

        PublicObjectiveCard card = new PublicObjectiveCard3();
        assertEquals(0, card.getScore(new WindowPanel(0,0)));
        assertEquals(20, card.getScore(TestPanels.panel_230()));
        assertEquals(10, card.getScore(TestPanels.panel_231()));
        assertEquals(0, card.getScore(TestPanels.panel_232()));

    }

    public static void card4() throws FileNotFoundException{

        PublicObjectiveCard card = new PublicObjectiveCard4();
        assertEquals(0, card.getScore(new WindowPanel(0,0)));
        assertEquals(20, card.getScore(TestPanels.panel_240()));
        assertEquals(8, card.getScore(TestPanels.panel_241()));
        assertEquals(0, card.getScore(TestPanels.panel_242()));

    }

    @Test
    public static void card5() throws FileNotFoundException {

        PublicObjectiveCard card = new PublicObjectiveCard5();

        assertEquals(0, card.getScore(new WindowPanel(0,0))); //empty panel
        assertEquals(0, card.getScore(TestPanels.panel_60()));
        assertEquals(0, card.getScore(TestPanels.panel_61()));
        assertEquals(0, card.getScore(TestPanels.panel_70()));
        assertEquals(0, card.getScore(TestPanels.panel_71()));
        assertEquals(10*2, card.getScore(TestPanels.panel_50())); //master panel
        assertEquals(5*2, card.getScore(TestPanels.panel_51()));

    }

    @Test
    public static void card6() throws FileNotFoundException {

        PublicObjectiveCard card = new PublicObjectiveCard6();
        assertEquals(0, card.getScore(new WindowPanel(0,0))); //empty panel
        assertEquals(0, card.getScore(TestPanels.panel_50()));
        assertEquals(0, card.getScore(TestPanels.panel_51()));
        assertEquals(0, card.getScore(TestPanels.panel_70()));
        assertEquals(0, card.getScore(TestPanels.panel_71()));
        assertEquals(10*2, card.getScore(TestPanels.panel_60())); //master panel
        assertEquals(5*2, card.getScore(TestPanels.panel_61()));

    }

    @Test
    public static void card7() throws FileNotFoundException {

        PublicObjectiveCard card = new PublicObjectiveCard7();
        assertEquals(0, card.getScore(new WindowPanel(0,0))); //empty panel
        assertEquals(0, card.getScore(TestPanels.panel_50()));
        assertEquals(0, card.getScore(TestPanels.panel_51()));
        assertEquals(0, card.getScore(TestPanels.panel_60()));
        assertEquals(0, card.getScore(TestPanels.panel_61()));
        assertEquals(10*2, card.getScore(TestPanels.panel_70())); //master panel
        assertEquals(5*2, card.getScore(TestPanels.panel_71()));

    }

    /**
     *
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
