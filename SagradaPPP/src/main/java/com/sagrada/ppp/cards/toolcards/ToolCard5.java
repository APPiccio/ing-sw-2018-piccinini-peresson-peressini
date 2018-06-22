package com.sagrada.ppp.cards.toolcards;

import com.sagrada.ppp.cards.ToolCardParameterContainer;
import com.sagrada.ppp.model.Color;
import com.sagrada.ppp.model.Dice;
import com.sagrada.ppp.model.UseToolCardResult;
import com.sagrada.ppp.utils.StaticValues;

public class ToolCard5 extends ToolCard {

    public ToolCard5() {
        super(StaticValues.TOOL_CARD_5_NAME, 5, Color.GREEN);
    }

    @Override
    public UseToolCardResult use(ToolCardParameterContainer container) {
        setUsed();
        Dice draftPoolDice = container.draftPool.get(container.toolCardParameters.draftPoolDiceIndex);
        new CommandToolCard5(draftPoolDice, container.roundTrack,
                container.toolCardParameters.roundTrackRoundIndex, container.toolCardParameters.roundTrackDiceIndex).useCard();
        return new UseToolCardResult(true,this.getId(),this.getCost(), container.draftPool, container.roundTrack, container.players, null,null);
    }

    @Override
    public boolean paramsOk(ToolCardParameterContainer container) {
        Dice draftPoolDice = container.draftPool.get(container.toolCardParameters.draftPoolDiceIndex);
        return draftPoolDice != null;
    }
}