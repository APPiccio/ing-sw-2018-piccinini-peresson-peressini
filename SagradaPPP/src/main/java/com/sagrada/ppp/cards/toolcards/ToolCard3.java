package com.sagrada.ppp.cards.toolcards;

import com.sagrada.ppp.cards.ToolCardParameterContainer;
import com.sagrada.ppp.model.*;
import com.sagrada.ppp.utils.StaticValues;
import javafx.util.Pair;

public class ToolCard3 extends ToolCard {

    public ToolCard3() {
        super(StaticValues.TOOL_CARD_3_NAME, 3, Color.RED);
    }

    @Override
    public UseToolCardResult use(ToolCardParameterContainer container) {
        setUsed();
        new CommandToolCard3(new Pair<>(container.toolCardParameters.panelDiceIndex,container.toolCardParameters.panelCellIndex),container.player.getPanel()).useCard();

        return new UseToolCardResult(true,this.getId(),this.getCost(), container.draftPool, container.roundTrack, container.players, null,null);
    }

    @Override
    public boolean paramsOk(ToolCardParameterContainer container) {
        Player player = new Player(container.player);
        ToolCardParameters toolCardParameters = container.toolCardParameters;
        WindowPanel windowPanel = player.getPanel();
        if (windowPanel == null) return false;
        Cell cell = windowPanel.getCell(toolCardParameters.panelCellIndex);
        if (cell == null) return false;
        Cell diceCell = windowPanel.getCell(toolCardParameters.panelDiceIndex);
        if (diceCell == null) return false;
        Dice dice = diceCell.getDiceOn();
        if (dice == null) return false;
        windowPanel.removeDice(toolCardParameters.panelDiceIndex);
        return player.getPanel().diceOk(dice, toolCardParameters.panelCellIndex, false, true, false);
    }
}