package com.sagrada.ppp.cards.ToolCards;

import com.sagrada.ppp.model.Dice;

public class CommandToolCard10 implements CommandToolCard{

    private Dice dice;

    public CommandToolCard10(Dice dice) {
        this.dice = dice;
    }

    public void useCard(){
        if(dice != null){
            dice.setValue(7 - dice.getValue());
        }
    }
}
