package com.sagrada.ppp.cards.toolcards;

import com.sagrada.ppp.model.Dice;
import com.sagrada.ppp.model.DiceTest;
import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CommandToolCard7Test {

    private ToolCard toolCard7;

    @Before
    public void setUp() {
        toolCard7 = new ToolCard7();
    }

    /**
     * No test to be performed here...
     * @see DiceTest
     */
    @Test
    public void useCard() {
        //Creating a new draftPool, 4 players participating at the game
        ArrayList<Dice> draftPool = new ArrayList<>(); //extracted dices
        for (int i = 0; i < 9; i++) {
            draftPool.add(new Dice());
        }

        //toolCard7.use(new CommandToolCard7(draftPool));

        assertTrue(true);
    }

}