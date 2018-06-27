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
        LinkedHashMap<Integer, Integer> positions = new LinkedHashMap<>();
        positions.put(container.toolCardParameters.panelDiceIndex , container.toolCardParameters.panelCellIndex);
        if(container.toolCardParameters.twoDiceAction) {
            positions.put(container.toolCardParameters.secondPanelDiceIndex, container.toolCardParameters.secondPanelCellIndex);
        }
        new CommandToolCard12(positions, container.player.getPanel()).useCard();
        return new UseToolCardResult(true,this.getId(),this.getCost(), container.draftPool, container.roundTrack, container.players, null,null);
    }

    @Override
    public boolean paramsOk(ToolCardParameterContainer container) {
        Player player = new Player(container.player);
        ToolCardParameters toolCardParameters = container.toolCardParameters;
        WindowPanel panel = new WindowPanel(player.getPanel());

        if ((toolCardParameters.panelCellIndex == toolCardParameters.secondPanelCellIndex) ||
                (toolCardParameters.panelDiceIndex == toolCardParameters.secondPanelDiceIndex)) return false;

        if (toolCardParameters.roundTrackRoundIndex < 1 || toolCardParameters.roundTrackRoundIndex > 10 ||
                toolCardParameters.roundTrackDiceIndex < 0 ||
                toolCardParameters.roundTrackDiceIndex >=
                        container.roundTrack.
                                getDicesOnRound(toolCardParameters.roundTrackRoundIndex).size()) return false;
        Dice dice = container.roundTrack.getDice(toolCardParameters.roundTrackRoundIndex,
                toolCardParameters.roundTrackDiceIndex);
        Color color = dice.getColor();

        Cell cell1start = panel.getCell(toolCardParameters.panelDiceIndex);
        Cell cell1end = panel.getCell(toolCardParameters.panelCellIndex);
        if (cell1start == null || cell1end == null || !cell1start.hasDiceOn() || cell1end.hasDiceOn() ||
                !cell1start.getDiceOn().getColor().equals(color)) return false;

        Dice movingDice1 = panel.removeDice(toolCardParameters.panelDiceIndex);
        if (!panel.addDice(toolCardParameters.panelCellIndex, movingDice1)) return false;

        if (toolCardParameters.twoDiceAction) {
            Cell cell2start = panel.getCell(toolCardParameters.secondPanelDiceIndex);
            Cell cell2end = panel.getCell(toolCardParameters.secondPanelCellIndex);
            if (cell2start == null || cell2end == null || !cell2start.hasDiceOn() || cell2end.hasDiceOn() ||
                    !cell2start.getDiceOn().getColor().equals(color)) return false;

            Dice movingDice2 = panel.removeDice(toolCardParameters.secondPanelDiceIndex);
            return panel.addDice(toolCardParameters.secondPanelCellIndex, movingDice2);
        }
        return true;
    }

}