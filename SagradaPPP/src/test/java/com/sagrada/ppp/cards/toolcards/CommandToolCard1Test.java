package com.sagrada.ppp.cards.toolcards;

import com.sagrada.ppp.model.Color;
import com.sagrada.ppp.model.Dice;
import org.junit.*;

import static org.junit.Assert.*;

public class CommandToolCard1Test {

    private ToolCard toolCard1;

    @Before
    public void setUp() {
        toolCard1 = new ToolCard1();
    }

    @Test
    public void useCard() {
        Dice dice = new Dice(Color.GREEN, 1);
        Dice tempDice = new Dice(dice);

        toolCard1.use(new CommandToolCard1(tempDice, 1));
        assertEquals(dice.getValue() + 1, tempDice.getValue());

        dice.setValue(2);
        tempDice.setValue(2);
        toolCard1.use(new CommandToolCard1(tempDice, 1));
        assertEquals(dice.getValue() + 1, tempDice.getValue());

        dice.setValue(3);
        tempDice.setValue(3);
        toolCard1.use(new CommandToolCard1(tempDice, 1));
        assertEquals(dice.getValue() + 1, tempDice.getValue());

        dice.setValue(4);
        tempDice.setValue(4);
        toolCard1.use(new CommandToolCard1(tempDice, 1));
        assertEquals(dice.getValue() + 1, tempDice.getValue());

        dice.setValue(5);
        tempDice.setValue(5);
        toolCard1.use(new CommandToolCard1(tempDice, 1));
        assertEquals(dice.getValue() + 1, tempDice.getValue());

        dice.setValue(6);
        tempDice.setValue(6);
        toolCard1.use(new CommandToolCard1(tempDice, 1));
        assertEquals(dice.getValue(), tempDice.getValue());

        dice.setValue(5);
        tempDice.setValue(5);
        toolCard1.use(new CommandToolCard1(tempDice, -1));
        assertEquals(dice.getValue() -1, tempDice.getValue());

        dice.setValue(4);
        tempDice.setValue(4);
        toolCard1.use(new CommandToolCard1(tempDice, -1));
        assertEquals(dice.getValue() -1, tempDice.getValue());

        dice.setValue(3);
        tempDice.setValue(3);
        toolCard1.use(new CommandToolCard1(tempDice, -1));
        assertEquals(dice.getValue() -1, tempDice.getValue());

        dice.setValue(2);
        tempDice.setValue(2);
        toolCard1.use(new CommandToolCard1(tempDice, -1));
        assertEquals(dice.getValue() -1, tempDice.getValue());

        dice.setValue(1);
        tempDice.setValue(1);
        toolCard1.use(new CommandToolCard1(tempDice, -1));
        assertEquals(dice.getValue(), tempDice.getValue());

        dice.setValue(6);
        tempDice.setValue(6);
        toolCard1.use(new CommandToolCard1(tempDice, -1));
        assertEquals(dice.getValue() -1, tempDice.getValue());
    }

}