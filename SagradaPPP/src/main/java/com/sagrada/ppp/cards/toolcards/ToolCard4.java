package com.sagrada.ppp.cards.toolcards;

import com.sagrada.ppp.cards.ToolCardParameterContainer;
import com.sagrada.ppp.model.*;
import com.sagrada.ppp.utils.StaticValues;

import java.util.LinkedHashMap;

public class ToolCard4 extends ToolCard {

    public ToolCard4() {
        super(StaticValues.TOOL_CARD_4_NAME, 4, Color.YELLOW);
    }

    @Override
    public UseToolCardResult use(ToolCardParameterContainer container) {
        setUsed();
        LinkedHashMap<Integer,Integer> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put(container.toolCardParameters.panelDiceIndex,container.toolCardParameters.panelCellIndex);
        linkedHashMap.put(container.toolCardParameters.secondPanelDiceIndex,container.toolCardParameters.secondPanelCellIndex);
        new CommandToolCard4(linkedHashMap,container.player.getPanel()).useCard();
        return new UseToolCardResult(true,this.getId(),this.getCost(), container.draftPool, container.roundTrack, container.players, null,null);
    }

    @Override
    public boolean paramsOk(ToolCardParameterContainer container) {
        Player player = new Player( container.player);
        ToolCardParameters toolCardParameters = container.toolCardParameters;
        WindowPanel windowPanel = player.getPanel();
        if (windowPanel == null) return false;
        Cell cell = windowPanel.getCell(toolCardParameters.panelCellIndex);
        if (cell == null) return false;
        Cell secondCell = windowPanel.getCell(toolCardParameters.secondPanelCellIndex);
        if (secondCell == null) return false;
        Cell diceCell = windowPanel.getCell(toolCardParameters.panelDiceIndex);
        if (diceCell == null) return false;
        Dice dice = diceCell.getDiceOn();
        if (dice == null) return false;
        windowPanel.removeDice(toolCardParameters.panelDiceIndex);
        if (!windowPanel.addDice(toolCardParameters.panelCellIndex,dice)) return false;
        Cell secondDiceCell = windowPanel.getCell(toolCardParameters.secondPanelDiceIndex);
        if (secondDiceCell == null) return false;
        Dice secondDice = secondDiceCell.getDiceOn();
        if (secondDice == null) return false;
        windowPanel.removeDice(toolCardParameters.secondPanelDiceIndex);
        return windowPanel.addDice(toolCardParameters.secondPanelCellIndex, secondDice);
    }
}