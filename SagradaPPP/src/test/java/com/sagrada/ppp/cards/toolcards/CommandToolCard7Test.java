package com.sagrada.ppp.cards.toolcards;

import com.sagrada.ppp.cards.ToolCardParameterContainer;
import com.sagrada.ppp.model.Color;
import com.sagrada.ppp.model.Dice;
import com.sagrada.ppp.model.DiceTest;
import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CommandToolCard7Test {

    private ToolCard toolCard7;
    private ToolCardParameterContainer container;

    @Before
    public void setUp() {
        toolCard7 = new ToolCard7();
        container = new ToolCardParameterContainer();
    }

    /**
     * No test to be performed here...
     * @see DiceTest
     */
    @Test
    public void useCard() {
        //Creating a new draftPool, 4 players participating at the game
        ArrayList<Dice> draftPool = new ArrayList<>(); //extracted dices
        ArrayList<Dice> dpCopy = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            Dice tempDice = new Dice();
            draftPool.add(tempDice);
            dpCopy.add(tempDice);
        }

        container.draftPool = dpCopy;
        assertTrue(toolCard7.paramsOk(container));
        toolCard7.use(container);

        for(Color color : Color.values()){
            assertEquals(draftPool.stream().filter(x -> x.getColor() == color).count() , dpCopy.stream().filter(x -> x.getColor() == color).count());
        }

    }

}