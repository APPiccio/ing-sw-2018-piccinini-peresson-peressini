package com.sagrada.ppp.cards.toolcards;

import com.sagrada.ppp.model.Color;
import com.sagrada.ppp.model.Dice;
import org.junit.*;

import static org.junit.Assert.*;

public class CommandToolCard10Test {

    private ToolCard toolCard10;

    @Before
    public void setUp() {
        toolCard10 = new ToolCard10();
    }

    @Test
    public void useCard() {
        Dice dice = new Dice(Color.PURPLE, 1);
        Dice trash = new Dice(dice);

        //toolCard10.use(new CommandToolCard10(trash));

        assertEquals(7 - dice.getValue(), trash.getValue());

        dice.setValue(2);
        trash.setValue(2);
        //toolCard10.use(new CommandToolCard10(trash));
        assertEquals(7 - dice.getValue(), trash.getValue());

        dice.setValue(3);
        trash.setValue(3);
        //toolCard10.use(new CommandToolCard10(trash));
        assertEquals(7 - dice.getValue(), trash.getValue());

        dice.setValue(4);
        trash.setValue(4);
        //toolCard10.use(new CommandToolCard10(trash));
        assertEquals(7 - dice.getValue(), trash.getValue());

        dice.setValue(5);
        trash.setValue(5);
        //toolCard10.use(new CommandToolCard10(trash));
        assertEquals(7 - dice.getValue(), trash.getValue());

        dice.setValue(6);
        trash.setValue(6);
        //toolCard10.use(new CommandToolCard10(trash));
        assertEquals(7 - dice.getValue(), trash.getValue());
    }

}