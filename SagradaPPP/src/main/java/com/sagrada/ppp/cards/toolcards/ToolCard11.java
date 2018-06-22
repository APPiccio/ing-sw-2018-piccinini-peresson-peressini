package com.sagrada.ppp.cards.toolcards;

import com.sagrada.ppp.cards.ToolCardParameterContainer;
import com.sagrada.ppp.model.Color;
import com.sagrada.ppp.model.Dice;
import com.sagrada.ppp.model.UseToolCardResult;
import com.sagrada.ppp.utils.StaticValues;

public class ToolCard11 extends ToolCard {

    public ToolCard11() {
        super(StaticValues.TOOL_CARD_11_NAME, 11, Color.PURPLE);
    }

    @Override
    public UseToolCardResult use(ToolCardParameterContainer container) {
        setUsed();
        Dice draftDice = container.draftPool.get(container.toolCardParameters.draftPoolDiceIndex);

        container.draftPool.remove((int) container.toolCardParameters.draftPoolDiceIndex);
        new CommandToolCard11(container.diceBag,draftDice).useCard();
        return new UseToolCardResult(true,this.getId(),this.getCost(),container.draftPool,container.roundTrack,container.players, draftDice, null);
    }

    @Override
    public boolean paramsOk(ToolCardParameterContainer container) {
        return container.diceBag.size() >= 1;
    }
}