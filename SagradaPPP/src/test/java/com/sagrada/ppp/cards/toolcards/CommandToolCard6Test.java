package com.sagrada.ppp.cards.toolcards;

import com.sagrada.ppp.model.Dice;
import org.junit.*;

import static org.junit.Assert.*;

public class CommandToolCard6Test {

    private ToolCard toolCard6;

    @Before
    public void setUp() {
        toolCard6 = new ToolCard6();
    }

    @Test
    public void useCard() {
        Dice oldDice = new Dice();
        Dice newDice = new Dice(oldDice);

        assertNotEquals(oldDice.hashCode(), newDice.hashCode());
        assertEquals(oldDice.getColor(), newDice.getColor());
        assertEquals(oldDice.getValue(), newDice.getValue());

        toolCard6.use(new CommandToolCard6(newDice));

        assertEquals(oldDice.getColor(), newDice.getColor());
        assertTrue(newDice.getValue() >= 1 || newDice.getValue() <=6);
    }

}