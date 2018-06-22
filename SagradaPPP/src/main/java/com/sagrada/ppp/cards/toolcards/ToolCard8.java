package com.sagrada.ppp.cards.toolcards;

import com.sagrada.ppp.cards.ToolCardParameterContainer;
import com.sagrada.ppp.model.*;
import com.sagrada.ppp.utils.StaticValues;

public class ToolCard8 extends ToolCard {

    public ToolCard8() {
        super(StaticValues.TOOL_CARD_8_NAME, 8, Color.RED);
    }

    @Override
    public UseToolCardResult use(ToolCardParameterContainer container) {
        new CommandToolCard8(container.toolCardParameters.panelCellIndex, container.player.getPanel(),
                container.toolCardParameters.draftPoolDiceIndex, container.draftPool).useCard();
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
        if (!(windowPanel.diceOk(dice,container.toolCardParameters.panelCellIndex))) return false;
        return true;
    }
}