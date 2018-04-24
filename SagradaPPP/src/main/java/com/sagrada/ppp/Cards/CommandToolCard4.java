package com.sagrada.ppp.Cards;

import com.sagrada.ppp.*;

public class CommandToolCard4 implements CommandToolCard {

    private int oldPosition1;
    private int newPosition1;
    private int oldPosition2;
    private int newPosition2;
    private WindowPanel windowPanel;

    public CommandToolCard4(int oldPosition1, int newPosition1, int oldPosition2, int newPosition2, WindowPanel windowPanel) {
        this.oldPosition1 = oldPosition1;
        this.newPosition1 = newPosition1;
        this.oldPosition2 = oldPosition2;
        this.newPosition2 = newPosition2;
        this.windowPanel = windowPanel;
    }

    @Override
    public void useCard() {
        Dice dice1 = windowPanel.getCellWithIndex(oldPosition1).getDiceOn();
        Dice dice2 = windowPanel.getCellWithIndex(oldPosition2).getDiceOn();
        if (dice1 != null && dice2 != null) {
            windowPanel.addDiceOnCellWithIndex(newPosition1, windowPanel.removeDice(oldPosition1));
            windowPanel.addDiceOnCellWithIndex(newPosition2, windowPanel.removeDice(oldPosition2));
        }
    }

}