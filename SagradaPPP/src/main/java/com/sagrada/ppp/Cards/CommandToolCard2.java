package com.sagrada.ppp.Cards;

import com.sagrada.ppp.*;
import javafx.util.Pair;

//Move any one die in your window ignoring color restriction

public class CommandToolCard2 implements CommandToolCard {

    private Pair<Integer, Integer> positions; //Pair <oldIndex, newIndex>
    private WindowPanel windowPanel;

    public CommandToolCard2(Pair<Integer, Integer> positions, WindowPanel windowPanel) {
        this.positions = positions;
        this.windowPanel = windowPanel;
    }

    @Override
    public void useCard() {
        windowPanel.addDiceOnCellWithIndex(positions.getValue(), windowPanel.removeDice(positions.getKey()),
                true, false, false);
    }

}