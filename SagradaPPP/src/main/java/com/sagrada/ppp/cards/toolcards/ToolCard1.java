package com.sagrada.ppp.cards.toolcards;

import com.sagrada.ppp.cards.ToolCardParameterContainer;
import com.sagrada.ppp.model.Color;
import com.sagrada.ppp.model.Dice;
import com.sagrada.ppp.model.UseToolCardResult;
import com.sagrada.ppp.utils.StaticValues;

public class ToolCard1 extends ToolCard {
    private Dice dice;

    public ToolCard1() {
        super(StaticValues.TOOL_CARD_1_NAME, 1, Color.PURPLE);
    }

    @Override
    public UseToolCardResult use(ToolCardParameterContainer container) {
        setUsed();
        new CommandToolCard1(dice,container.toolCardParameters.actionSign).useCard();

        return new UseToolCardResult(true,this.getId(),this.getCost(), container.draftPool, container.roundTrack, container.players, null,null);
    }

    @Override
    public boolean paramsOk(ToolCardParameterContainer container) {
        dice = container.draftPool.get(container.toolCardParameters.draftPoolDiceIndex);
        if (dice == null) return false;
        if (dice.getValue() == 1 && container.toolCardParameters.actionSign == -1) return false;
        if (dice.getValue() == 6 && container.toolCardParameters.actionSign == +1) return false;
        return true;
    }
}