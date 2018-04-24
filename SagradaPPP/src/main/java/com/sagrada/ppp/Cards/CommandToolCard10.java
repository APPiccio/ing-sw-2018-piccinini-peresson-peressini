package com.sagrada.ppp.Cards;

import com.sagrada.ppp.*;

public class CommandToolCard10 {

    private int dicePosition;
    private WindowPanel windowPanel;

    public CommandToolCard10(int dicePosition){
        this.dicePosition = dicePosition;
    }

    public WindowPanel useCard(){
        Dice dice = windowPanel.getCellWithIndex(dicePosition).getDiceOn();
        if(dice != null){
            dice.setValue(7 - dice.getValue());
            windowPanel.setDice(dicePosition, dice);
        }
        return windowPanel;
    }
}
