package com.sagrada.ppp.cards.toolcards;

import com.sagrada.ppp.cards.ToolCardParameterContainer;
import com.sagrada.ppp.model.Color;
import com.sagrada.ppp.model.Dice;
import com.sagrada.ppp.model.UseToolCardResult;
import com.sagrada.ppp.utils.StaticValues;

public class ToolCard6 extends ToolCard {

    public ToolCard6() {
        super(StaticValues.TOOL_CARD_6_NAME, 6, Color.PURPLE);
    }

    @Override
    public UseToolCardResult use(ToolCardParameterContainer container) {
        setUsed();
        Dice reRoll = container.draftPool.get(container.toolCardParameters.draftPoolDiceIndex);
        container.draftPool.remove((int) container.toolCardParameters.draftPoolDiceIndex);
        new CommandToolCard6(reRoll).useCard();
        return new UseToolCardResult(true,this.getId(),this.getCost(),container.draftPool,container.roundTrack,container.players, reRoll, null);
    }

    @Override
    public boolean paramsOk(ToolCardParameterContainer container) {
        Dice reRoll = container.draftPool.get(container.toolCardParameters.draftPoolDiceIndex);
        return reRoll != null;
    }
}
