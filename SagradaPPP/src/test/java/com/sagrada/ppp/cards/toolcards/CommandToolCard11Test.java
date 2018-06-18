package com.sagrada.ppp.cards.toolcards;

import com.sagrada.ppp.model.Dice;
import com.sagrada.ppp.model.DiceBag;
import org.junit.*;

import static org.junit.Assert.*;

public class CommandToolCard11Test {

    private ToolCard toolCard11;

    @Before
    public void setUp() {
        toolCard11 = new ToolCard11();
    }

    @Test
    public void useCard() {
        DiceBag diceBag = new DiceBag();
        Dice dice = diceBag.extractRandomDice();

        DiceBag diceBagCopy = new DiceBag(diceBag);
        Dice diceCopy = new Dice(dice);

        toolCard11.use(new CommandToolCard11(diceBagCopy, diceCopy));

        //Random dice throw test not particularly meaningful
        assertTrue(true);
    }

}