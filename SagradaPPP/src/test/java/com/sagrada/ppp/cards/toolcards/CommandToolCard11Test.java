package com.sagrada.ppp.cards.toolcards;

import com.sagrada.ppp.cards.ToolCardParameterContainer;
import com.sagrada.ppp.model.Dice;
import com.sagrada.ppp.model.DiceBag;
import com.sagrada.ppp.model.DiceBagTest;
import com.sagrada.ppp.model.ToolCardParameters;
import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CommandToolCard11Test {

    private ToolCard toolCard11;
    private ToolCardParameterContainer container;

    @Before
    public void setUp() {
        toolCard11 = new ToolCard11();
        container = new ToolCardParameterContainer();
        container.toolCardParameters = new ToolCardParameters();
    }

    /**
     * @see DiceBagTest for other tests on methods used by this toolcard
     */
    @Test
    public void useCard() {
        DiceBag diceBag = new DiceBag();
        Dice dice = diceBag.extractRandomDice();

        DiceBag diceBagCopy = new DiceBag(diceBag);
        Dice diceCopy = new Dice(dice);

        container.diceBag = diceBagCopy;
        container.draftPool = new ArrayList<>();
        container.draftPool.add(diceCopy);
        container.toolCardParameters.draftPoolDiceIndex = 0;


        //toolCard11.use(new CommandToolCard11(diceBagCopy, diceCopy));

        assertTrue(toolCard11.paramsOk(container));
        toolCard11.use(container);
        assertTrue(true);
    }

}