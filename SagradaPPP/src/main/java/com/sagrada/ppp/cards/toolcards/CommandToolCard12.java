package com.sagrada.ppp.cards.toolcards;

import com.sagrada.ppp.model.WindowPanel;

import java.util.LinkedHashMap;

public class CommandToolCard12 implements CommandToolCard {

    private LinkedHashMap<Integer, Integer> positions;
    private WindowPanel windowPanel;

    public CommandToolCard12(LinkedHashMap<Integer,Integer> positions, WindowPanel windowPanel){
        this.positions = positions;
        this.windowPanel = windowPanel;
    }

    public void useCard() {
        for(Integer pos : positions.keySet())
            windowPanel.addDice(positions.get(pos), windowPanel.removeDice(pos));
    }

}
