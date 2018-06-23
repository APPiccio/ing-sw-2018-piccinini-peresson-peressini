package com.sagrada.ppp.cards.toolcards;

import com.sagrada.ppp.cards.ToolCardParameterContainer;
import com.sagrada.ppp.model.Color;
import com.sagrada.ppp.model.Dice;
import com.sagrada.ppp.model.ToolCardParameters;
import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CommandToolCard10Test {

    private ToolCard toolCard10;
    private ToolCardParameterContainer container;

    @Before
    public void setUp() {
        toolCard10 = new ToolCard10();
        container = new ToolCardParameterContainer();
        container.toolCardParameters = new ToolCardParameters();
    }

    @Test
    public void useCard() {
        Dice dice = new Dice(Color.PURPLE, 1);
        Dice trash = new Dice(dice);
        ArrayList<Dice> draftPool = new ArrayList<>();
        draftPool.add(trash);

        container.toolCardParameters.draftPoolDiceIndex = 0;
        container.draftPool = draftPool;
        assertTrue(toolCard10.paramsOk(container));
        toolCard10.use(container);

        assertEquals(7 - dice.getValue(), trash.getValue());

        dice.setValue(2);
        trash.setValue(2);

        assertTrue(toolCard10.paramsOk(container));
        toolCard10.use(container);
        assertEquals(7 - dice.getValue(), trash.getValue());

        dice.setValue(3);
        trash.setValue(3);
        assertTrue(toolCard10.paramsOk(container));
        toolCard10.use(container);
        assertEquals(7 - dice.getValue(), trash.getValue());

        dice.setValue(4);
        trash.setValue(4);
        assertTrue(toolCard10.paramsOk(container));
        toolCard10.use(container);
        assertEquals(7 - dice.getValue(), trash.getValue());

        dice.setValue(5);
        trash.setValue(5);
        assertTrue(toolCard10.paramsOk(container));
        toolCard10.use(container);
        assertEquals(7 - dice.getValue(), trash.getValue());

        dice.setValue(6);
        trash.setValue(6);
        assertTrue(toolCard10.paramsOk(container));
        toolCard10.use(container);
        assertEquals(7 - dice.getValue(), trash.getValue());
    }

}