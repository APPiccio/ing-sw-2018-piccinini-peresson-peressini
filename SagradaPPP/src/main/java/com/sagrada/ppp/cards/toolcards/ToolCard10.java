package com.sagrada.ppp.cards.toolcards;

import com.sagrada.ppp.cards.ToolCardParameterContainer;
import com.sagrada.ppp.model.Color;
import com.sagrada.ppp.model.Dice;
import com.sagrada.ppp.model.UseToolCardResult;
import com.sagrada.ppp.utils.StaticValues;

public class ToolCard10 extends ToolCard {

    public ToolCard10() {
        super(StaticValues.TOOL_CARD_10_NAME, 10, Color.GREEN);
    }

    @Override
    public UseToolCardResult use(ToolCardParameterContainer container) {
        setUsed();
        Dice dice = container.draftPool.get(container.toolCardParameters.draftPoolDiceIndex);
        new CommandToolCard10(dice).useCard();
        return new UseToolCardResult(true,this.getId(),this.getCost(), container.draftPool, container.roundTrack, container.players, null,null);
    }

    @Override
    public boolean paramsOk(ToolCardParameterContainer container) {
        Dice dice = container.draftPool.get(container.toolCardParameters.draftPoolDiceIndex);
        return dice != null;
    }
}