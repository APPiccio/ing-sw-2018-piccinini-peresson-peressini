package com.sagrada.ppp.cards.ToolCards;

import com.sagrada.ppp.model.WindowPanel;
import javafx.util.Pair;

//Move any one die in your window ignoring shade (value) restriction

public class CommandToolCard3 implements CommandToolCard {

    private Pair<Integer, Integer> positions; //Pair <oldIndex, newIndex>
    private WindowPanel windowPanel;

    public CommandToolCard3(Pair<Integer, Integer> positions, WindowPanel windowPanel) {
        this.positions = positions;
        this.windowPanel = windowPanel;
    }

    @Override
    public void useCard() {
        windowPanel.addDice(positions.getValue(), windowPanel.removeDice(positions.getKey()),
                false, true, false);
    }

}