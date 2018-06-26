package com.sagrada.ppp.cards.toolcards;

import com.sagrada.ppp.model.WindowPanel;

import java.util.LinkedHashMap;

public class CommandToolCard4 implements CommandToolCard {

    /**
     * positions<oldDiceIndex, newDiceIndex>
     */
    private LinkedHashMap<Integer, Integer> positions;
    private WindowPanel windowPanel;

    CommandToolCard4(LinkedHashMap<Integer, Integer> positions, WindowPanel windowPanel) {
        this.positions = positions;
        this.windowPanel = windowPanel;
    }

    /**
     * This method moves exactly two dices from their oldDiceIndex position to their newDiceIndex position
     */
    @Override
    public void useCard() {
        for (Integer pos : positions.keySet()) {
            windowPanel.addDice(positions.get(pos), windowPanel.removeDice(pos));
        }
    }

}