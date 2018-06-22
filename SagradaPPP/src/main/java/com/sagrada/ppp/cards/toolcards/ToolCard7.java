package com.sagrada.ppp.cards.toolcards;

import com.sagrada.ppp.cards.ToolCardParameterContainer;
import com.sagrada.ppp.model.Color;
import com.sagrada.ppp.model.UseToolCardResult;
import com.sagrada.ppp.utils.StaticValues;

public class ToolCard7 extends ToolCard {

    public ToolCard7() {
        super(StaticValues.TOOL_CARD_7_NAME, 7, Color.BLUE);
    }

    @Override
    public UseToolCardResult use(ToolCardParameterContainer container) {
        new CommandToolCard7(container.draftPool).useCard();
        return new UseToolCardResult(true,this.getId(),this.getCost(), container.draftPool, container.roundTrack, container.players, null,null);
    }

    @Override
    public boolean paramsOk(ToolCardParameterContainer container) {
        return true;
    }
}