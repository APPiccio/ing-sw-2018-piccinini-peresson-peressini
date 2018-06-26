package com.sagrada.ppp.cards.toolcards;

import com.sagrada.ppp.cards.ToolCardParameterContainer;
import com.sagrada.ppp.model.*;
import com.sagrada.ppp.utils.StaticValues;

public class ToolCard9 extends ToolCard {

    public ToolCard9() {
        super(StaticValues.TOOL_CARD_9_NAME, 9, Color.YELLOW);
    }

    @Override
    public UseToolCardResult use(ToolCardParameterContainer container) {
        setUsed();
        new CommandToolCard9(container.player,container.toolCardParameters.panelCellIndex,container.draftPool.get(container.toolCardParameters.draftPoolDiceIndex)).useCard();
        return new UseToolCardResult(true,this.getId(),this.getCost(), container.draftPool, container.roundTrack, container.players, null,null);
    }

    @Override
    public boolean paramsOk(ToolCardParameterContainer container) {
        Player player = new Player(container.player);
        WindowPanel windowPanel = player.getPanel();
        if (windowPanel == null) return false;
        Cell cell = windowPanel.getCell(container.toolCardParameters.panelCellIndex);
        if (cell == null) return false;
        Dice dice = container.draftPool.get(container.toolCardParameters.draftPoolDiceIndex);
        if (dice == null) return false;
        return windowPanel.diceOk(dice, container.toolCardParameters.panelCellIndex, false, false, true);
    }
}