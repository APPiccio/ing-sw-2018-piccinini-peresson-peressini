package com.sagrada.ppp.cards.toolcards;

import com.sagrada.ppp.model.WindowPanel;
import javafx.util.Pair;

public class CommandToolCard3 implements CommandToolCard {

    /**
     * positions<oldDiceIndex, newDiceIndex>
     */
    private Pair<Integer, Integer> positions;
    private WindowPanel windowPanel;

    public CommandToolCard3(Pair<Integer, Integer> positions, WindowPanel windowPanel) {
        this.positions = positions;
        this.windowPanel = windowPanel;
    }

    /**
     * Move the dice in oldDiceIndex position to newDiceIndex position,
     * ignoring shade restriction
     */
    @Override
    public void useCard() {
        windowPanel.addDice(positions.getValue(), windowPanel.removeDice(positions.getKey()),
                false, true, false);
    }

}