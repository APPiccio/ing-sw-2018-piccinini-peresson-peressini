package com.sagrada.ppp.cards.toolcards;

import com.sagrada.ppp.model.Dice;
import com.sagrada.ppp.model.WindowPanel;

import java.util.ArrayList;

public class CommandToolCard8 implements CommandToolCard {

    private int cellIndex;
    private WindowPanel windowPanel;
    private int draftPoolDiceIndex;
    private ArrayList<Dice> draftPool;

    public CommandToolCard8(int index, WindowPanel windowPanel, int draftPoolDiceIndex, ArrayList<Dice> draftPool) {
        this.cellIndex = index;
        this.windowPanel = windowPanel;
        this.draftPoolDiceIndex = draftPoolDiceIndex;
        this.draftPool = draftPool;
    }

    @Override
    public void useCard() {
        windowPanel.addDice(cellIndex, draftPool.remove(draftPoolDiceIndex) ,
                false, false, false);

    }
}
