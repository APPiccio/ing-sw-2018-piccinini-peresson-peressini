package com.sagrada.ppp.cards.toolcards;

import com.sagrada.ppp.cards.ToolCardParameterContainer;
import com.sagrada.ppp.model.Color;
import com.sagrada.ppp.model.Dice;
import com.sagrada.ppp.model.ToolCardParameters;
import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CommandToolCard1Test {

    private ToolCard toolCard1;
    private ToolCardParameterContainer container;

    @Before
    public void setUp() {
        toolCard1 = new ToolCard1();
        container = new ToolCardParameterContainer();
        container.draftPool = new ArrayList<>();
        container.toolCardParameters = new ToolCardParameters();
        container.toolCardParameters.actionSign = 1;
    }

    @Test
    public void useCard() {
        Dice dice = new Dice(Color.GREEN, 1);
        Dice tempDice = new Dice(dice);
        container.draftPool.add(tempDice);
        container.toolCardParameters.draftPoolDiceIndex = 0;
        toolCard1.paramsOk(container);
        toolCard1.use(container);
        assertEquals(dice.getValue() + 1, tempDice.getValue());

        dice.setValue(2);
        tempDice.setValue(2);
        container.draftPool = new ArrayList<>();
        container.draftPool.add(tempDice);
        toolCard1.paramsOk(container);
        toolCard1.use(container);
        assertEquals(dice.getValue() + 1, tempDice.getValue());

        dice.setValue(3);
        tempDice.setValue(3);
        container.draftPool = new ArrayList<>();
        container.draftPool.add(tempDice);
        toolCard1.paramsOk(container);
        toolCard1.use(container);
        assertEquals(dice.getValue() + 1, tempDice.getValue());

        dice.setValue(4);
        tempDice.setValue(4);
        container.draftPool = new ArrayList<>();
        container.draftPool.add(tempDice);
        toolCard1.paramsOk(container);
        toolCard1.use(container);
        assertEquals(dice.getValue() + 1, tempDice.getValue());

        dice.setValue(5);
        tempDice.setValue(5);
        container.draftPool = new ArrayList<>();
        container.draftPool.add(tempDice);
        toolCard1.paramsOk(container);
        toolCard1.use(container);
        assertEquals(dice.getValue() + 1, tempDice.getValue());

        dice.setValue(6);
        tempDice.setValue(6);
        container.draftPool = new ArrayList<>();
        container.draftPool.add(tempDice);
        toolCard1.paramsOk(container);
        toolCard1.use(container);
        assertEquals(dice.getValue(), tempDice.getValue());

        dice.setValue(5);
        tempDice.setValue(5);
        container.toolCardParameters.actionSign = -1;
        container.draftPool = new ArrayList<>();
        container.draftPool.add(tempDice);
        toolCard1.paramsOk(container);
        toolCard1.use(container);
        assertEquals(dice.getValue() - 1, tempDice.getValue());

        dice.setValue(4);
        tempDice.setValue(4);
        container.toolCardParameters.actionSign = -1;
        container.draftPool = new ArrayList<>();
        container.draftPool.add(tempDice);
        toolCard1.paramsOk(container);
        toolCard1.use(container);
        assertEquals(dice.getValue() - 1, tempDice.getValue());

        dice.setValue(3);
        tempDice.setValue(3);
        container.toolCardParameters.actionSign = -1;
        container.draftPool = new ArrayList<>();
        container.draftPool.add(tempDice);
        toolCard1.paramsOk(container);
        toolCard1.use(container);
        assertEquals(dice.getValue() - 1, tempDice.getValue());

        dice.setValue(2);
        tempDice.setValue(2);
        container.toolCardParameters.actionSign = -1;
        container.draftPool = new ArrayList<>();
        container.draftPool.add(tempDice);
        toolCard1.paramsOk(container);
        toolCard1.use(container);
        assertEquals(dice.getValue() - 1, tempDice.getValue());

        dice.setValue(1);
        tempDice.setValue(1);
        container.toolCardParameters.actionSign = -1;
        container.draftPool = new ArrayList<>();
        container.draftPool.add(tempDice);
        toolCard1.paramsOk(container);
        toolCard1.use(container);
        assertEquals(dice.getValue(), tempDice.getValue());

        dice.setValue(6);
        tempDice.setValue(6);
        container.toolCardParameters.actionSign = -1;
        container.draftPool = new ArrayList<>();
        container.draftPool.add(tempDice);
        toolCard1.paramsOk(container);
        toolCard1.use(container);
        assertEquals(dice.getValue() - 1, tempDice.getValue());

        dice.setValue(1);
        tempDice.setValue(1);
        container.toolCardParameters.actionSign = -1;
        container.draftPool = new ArrayList<>();
        container.draftPool.add(tempDice);
        assertFalse(toolCard1.paramsOk(container));
        toolCard1.use(container);
        assertNotEquals(dice.getValue() - 1, tempDice.getValue());

        dice.setValue(6);
        tempDice.setValue(6);
        container.toolCardParameters.actionSign = +1;
        container.draftPool = new ArrayList<>();
        container.draftPool.add(tempDice);
        assertFalse(toolCard1.paramsOk(container));
        toolCard1.use(container);
        assertNotEquals(dice.getValue() + 1, tempDice.getValue());

        container.draftPool = new ArrayList<>();
        container.draftPool.add(null);
        assertFalse(toolCard1.paramsOk(container));
    }

}