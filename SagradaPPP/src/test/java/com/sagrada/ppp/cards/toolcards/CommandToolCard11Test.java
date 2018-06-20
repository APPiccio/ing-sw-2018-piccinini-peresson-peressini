package com.sagrada.ppp.cards.toolcards;

import com.sagrada.ppp.model.Dice;
import com.sagrada.ppp.model.DiceBag;
import com.sagrada.ppp.model.DiceBagTest;
import org.junit.*;

import static org.junit.Assert.*;

public class CommandToolCard11Test {

    private ToolCard toolCard11;

    @Before
    public void setUp() {
        toolCard11 = new ToolCard11();
    }

    /**
     * No test to be performed here...
     * @see DiceBagTest
     */
    @Test
    public void useCard() {
        DiceBag diceBag = new DiceBag();
        Dice dice = diceBag.extractRandomDice();

        DiceBag diceBagCopy = new DiceBag(diceBag);
        Dice diceCopy = new Dice(dice);

        toolCard11.use(new CommandToolCard11(diceBagCopy, diceCopy));

        assertTrue(true);
    }

}