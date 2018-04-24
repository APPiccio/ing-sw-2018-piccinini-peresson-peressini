package com.sagrada.ppp;


import com.sagrada.ppp.Cards.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class ToolCardTest {

    @Test
    public void testAllToolCards(){
        card1();
    }


    @Test
    public void card1(){
        Dice dice = new Dice(Color.GREEN, 1);
        Dice trash = new Dice(dice);

        //testing incrementing
        ToolCard toolCard1 = new ToolCard1();
        toolCard1.use(new CommandToolCard1(trash,1));
        assertEquals(dice.getValue() + 1, trash.getValue());

        dice.setValue(2);
        trash.setValue(2);
        toolCard1.use(new CommandToolCard1(trash,1));
        assertEquals(dice.getValue() + 1, trash.getValue());

        dice.setValue(3);
        trash.setValue(3);
        toolCard1.use(new CommandToolCard1(trash,1));
        assertEquals(dice.getValue() + 1, trash.getValue());

        dice.setValue(4);
        trash.setValue(4);
        toolCard1.use(new CommandToolCard1(trash,1));
        assertEquals(dice.getValue() + 1, trash.getValue());

        dice.setValue(5);
        trash.setValue(5);
        toolCard1.use(new CommandToolCard1(trash,1));
        assertEquals(dice.getValue() + 1, trash.getValue());

        dice.setValue(6);
        trash.setValue(6);
        toolCard1.use(new CommandToolCard1(trash,1));
        assertEquals(dice.getValue(), trash.getValue());

        dice.setValue(5);
        trash.setValue(5);
        toolCard1.use(new CommandToolCard1(trash,-1));
        assertEquals(dice.getValue() -1, trash.getValue());

        dice.setValue(4);
        trash.setValue(4);
        toolCard1.use(new CommandToolCard1(trash,-1));
        assertEquals(dice.getValue() -1, trash.getValue());

        dice.setValue(3);
        trash.setValue(3);
        toolCard1.use(new CommandToolCard1(trash,-1));
        assertEquals(dice.getValue() -1, trash.getValue());

        dice.setValue(2);
        trash.setValue(2);
        toolCard1.use(new CommandToolCard1(trash,-1));
        assertEquals(dice.getValue() -1, trash.getValue());

        dice.setValue(1);
        trash.setValue(1);
        toolCard1.use(new CommandToolCard1(trash,-1));
        assertEquals(dice.getValue(), trash.getValue());

        dice.setValue(6);
        trash.setValue(6);
        toolCard1.use(new CommandToolCard1(trash,-1));
        assertEquals(dice.getValue() -1, trash.getValue());


    }

    @Test
    public void card11(){
        DiceBag diceBag = new DiceBag();
        Dice dice = diceBag.extractRandomDice();

        DiceBag diceBagCopy = new DiceBag(diceBag);
        Dice diceCopy = new Dice(dice);

        ToolCard toolCard11 = new ToolCard11();
        toolCard11.use(new CommandToolCard11(diceBagCopy,diceCopy));

        //assertEquals(false, diceCopy.equals(dice));
        //assertEquals(false, diceBag.equals(diceBagCopy));

        System.out.println("dado da riserva = " + dice.toString());
        System.out.println("secondo dado = " + diceCopy.toString());


    }

    @Test
    public void card10(){
        Dice dice = new Dice(Color.PURPLE, 1);
        Dice trash = new Dice(dice);

        ToolCard toolCard10 = new ToolCard10();
        toolCard10.use(new CommandToolCard10(trash));

        assertEquals(7 - dice.getValue(), trash.getValue());

        dice.setValue(2);
        trash.setValue(2);
        toolCard10.use(new CommandToolCard10(trash));
        assertEquals(7 - dice.getValue(), trash.getValue());

        dice.setValue(3);
        trash.setValue(3);
        toolCard10.use(new CommandToolCard10(trash));
        assertEquals(7 - dice.getValue(), trash.getValue());

        dice.setValue(4);
        trash.setValue(4);
        toolCard10.use(new CommandToolCard10(trash));
        assertEquals(7 - dice.getValue(), trash.getValue());

        dice.setValue(5);
        trash.setValue(5);
        toolCard10.use(new CommandToolCard10(trash));
        assertEquals(7 - dice.getValue(), trash.getValue());

        dice.setValue(6);
        trash.setValue(6);
        toolCard10.use(new CommandToolCard10(trash));
        assertEquals(7 - dice.getValue(), trash.getValue());
    }
}
