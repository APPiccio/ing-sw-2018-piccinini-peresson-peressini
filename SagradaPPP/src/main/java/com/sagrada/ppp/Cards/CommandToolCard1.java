package com.sagrada.ppp.Cards;

import com.sagrada.ppp.*;

public class CommandToolCard1 implements CommandToolCard{

    private Dice dice;
    //1 aumenta, -1 decrementa
    private int sign;


    public CommandToolCard1(Dice dice, int sign) {
        this.dice = dice;
        this.sign = sign;
    }

    public void useCard(){
        if(dice != null) {
            if(!((dice.getValue() == 6 && sign == +1) || (dice.getValue() == 1 && sign == -1))){
                dice.setValue(dice.getValue() + sign);
            }
        }
    }
}