package com.sagrada.ppp.Cards;

import com.sagrada.ppp.WindowPanel;
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
