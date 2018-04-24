package com.sagrada.ppp.Cards;

import com.sagrada.ppp.*;

public class CommandToolCard1 implements CommandToolCard{

    private int dicePosition;
    //1 aumenta, -1 decrementa
    private int sign;
    private WindowPanel windowPanel;


    public CommandToolCard1(int dicePosition, int sign, WindowPanel windowPanel) {
        this.windowPanel = windowPanel;
        this.dicePosition = dicePosition;
        this.sign = sign;
    }

    public void useCard(){
        Dice dice = windowPanel.getCellWithIndex(dicePosition).getDiceOn();
        if(dice != null) {
            if(!((dice.getValue() == 6 && sign == +1) || (dice.getValue() == 1 && sign == -1))){
                dice.setValue(dice.getValue() + sign);
                windowPanel.setDice(dicePosition, dice);
            }
        }
    }
}