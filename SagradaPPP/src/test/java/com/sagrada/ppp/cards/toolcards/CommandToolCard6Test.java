package com.sagrada.ppp.cards.toolcards;

import com.sagrada.ppp.cards.ToolCardParameterContainer;
import com.sagrada.ppp.model.Dice;
import com.sagrada.ppp.model.ToolCardParameters;
import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CommandToolCard6Test {

    private ToolCard toolCard6;
    private ToolCardParameterContainer container;

    @Before
    public void setUp() {
        toolCard6 = new ToolCard6();
        container = new ToolCardParameterContainer();
        container.toolCardParameters = new ToolCardParameters();
    }

    @Test
    public void useCard() {
        Dice oldDice = new Dice();
        Dice newDice = new Dice(oldDice);

        assertNotEquals(oldDice.hashCode(), newDice.hashCode());
        assertEquals(oldDice.getColor(), newDice.getColor());
        assertEquals(oldDice.getValue(), newDice.getValue());
        ArrayList<Dice> draftPool = new ArrayList<>();
        draftPool.add(newDice);
        container.draftPool = draftPool;
        container.toolCardParameters.draftPoolDiceIndex = 0;
        assertTrue(toolCard6.paramsOk(container));
        toolCard6.use(container);
        assertEquals(oldDice.getColor(), newDice.getColor());
        assertTrue(newDice.getValue() >= 1 || newDice.getValue() <=6);

        //Testing paramsOk
        draftPool = new ArrayList<>();
        draftPool.add(null);
        container.draftPool = draftPool;
        container.toolCardParameters.draftPoolDiceIndex = 0;
        assertFalse(toolCard6.paramsOk(container));
    }

}