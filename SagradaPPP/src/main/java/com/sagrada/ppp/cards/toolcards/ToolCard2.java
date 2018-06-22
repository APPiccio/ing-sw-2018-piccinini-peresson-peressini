package com.sagrada.ppp.cards.toolcards;

import com.sagrada.ppp.cards.ToolCardParameterContainer;
import com.sagrada.ppp.model.*;
import com.sagrada.ppp.utils.StaticValues;
import javafx.util.Pair;

public class ToolCard2 extends ToolCard {

    public ToolCard2() {
        super(StaticValues.TOOL_CARD_2_NAME, 2, Color.BLUE);
    }

    @Override
    public UseToolCardResult use(ToolCardParameterContainer container) {
        setUsed();
        new CommandToolCard2(new Pair<>(container.toolCardParameters.panelDiceIndex,container.toolCardParameters.panelCellIndex),container.player.getPanel()).useCard();

        return new UseToolCardResult(true,this.getId(),this.getCost(), container.draftPool, container.roundTrack, container.players, null,null);
    }

    @Override
    public boolean paramsOk(ToolCardParameterContainer container) {
        Player player = new Player(container.player);
        WindowPanel windowPanel = player.getPanel();
        ToolCardParameters toolCardParameters = container.toolCardParameters;

        if (windowPanel == null) return false;
        Cell cell = windowPanel.getCell(toolCardParameters.panelCellIndex);
        if (cell == null) return false;
        Cell diceCell = windowPanel.getCell(toolCardParameters.panelDiceIndex);
        if (diceCell == null) return false;
        Dice dice = diceCell.getDiceOn();
        if (dice == null) return false;
        windowPanel.removeDice(toolCardParameters.panelDiceIndex);

        if (!player.getPanel().diceOk(dice,toolCardParameters.panelCellIndex,true,false,false)) return false;
        return true;
    }
}