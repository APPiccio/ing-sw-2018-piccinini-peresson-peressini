package com.sagrada.ppp.cards.toolcards;

import com.sagrada.ppp.cards.ToolCardParameterContainer;
import com.sagrada.ppp.model.*;
import com.sagrada.ppp.utils.StaticValues;

import java.util.LinkedHashMap;

public class ToolCard12 extends ToolCard {

    public ToolCard12() {
        super(StaticValues.TOOL_CARD_12_NAME, 12, Color.BLUE);
    }

    @Override
    public UseToolCardResult use(ToolCardParameterContainer container) {
        setUsed();
        WindowPanel panel = new WindowPanel(container.player.getPanel());
        LinkedHashMap<Integer, Integer> positions = new LinkedHashMap<>();
        positions.put(container.toolCardParameters.panelDiceIndex , container.toolCardParameters.panelCellIndex);
        if(container.toolCardParameters.twoDiceAction) {
            positions.put(container.toolCardParameters.secondPanelDiceIndex, container.toolCardParameters.secondPanelCellIndex);
        }
        new CommandToolCard12(positions, panel).useCard();
        container.player.setPanel(panel);
        return new UseToolCardResult(true,this.getId(),this.getCost(), container.draftPool, container.roundTrack, container.players, null,null);
    }

    @Override
    public boolean paramsOk(ToolCardParameterContainer container) {
        Player player = new Player(container.player);
        ToolCardParameters toolCardParameters = container.toolCardParameters;
        Cell cell1start = player.getPanel().getCell(toolCardParameters.panelDiceIndex);
        Cell cell1end = player.getPanel().getCell(toolCardParameters.panelCellIndex);
        Dice dice = container.roundTrack.getDice(toolCardParameters.roundTrackRoundIndex, toolCardParameters.roundTrackDiceIndex);
        if (dice == null || cell1end == null || cell1start == null) return false;
        Color color = dice.getColor();
        if((toolCardParameters.secondPanelCellIndex == toolCardParameters.panelCellIndex)||(toolCardParameters.panelCellIndex == toolCardParameters.secondPanelDiceIndex)) return false;
        if(!cell1start.hasDiceOn() || cell1end.hasDiceOn() || !cell1start.getDiceOn().getColor().equals(color)) return false;
        WindowPanel panel = player.getPanel();
        Dice movingDice1 = panel.removeDice(toolCardParameters.panelDiceIndex);
        if(!panel.addDice(toolCardParameters.panelCellIndex,movingDice1)) return false;
        if(toolCardParameters.twoDiceAction){
            Cell cell2start = player.getPanel().getCell(toolCardParameters.secondPanelDiceIndex);
            Cell cell2end = player.getPanel().getCell(toolCardParameters.secondPanelCellIndex);
            Dice movingDice2 = panel.removeDice(toolCardParameters.secondPanelDiceIndex);
            if(!panel.addDice(toolCardParameters.secondPanelCellIndex, movingDice2)) return false;
            if(cell2end == null || cell2start == null) return false;
            if(!cell2start.hasDiceOn() || cell2end.hasDiceOn() || !cell2start.getDiceOn().getColor().equals(color)) return false;
        }
        return true;
    }
}