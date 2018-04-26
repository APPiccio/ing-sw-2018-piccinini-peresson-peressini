package com.sagrada.ppp.Cards.ToolCards;

import com.sagrada.ppp.*;

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
