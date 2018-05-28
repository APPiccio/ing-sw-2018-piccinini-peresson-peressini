package com.sagrada.ppp.cards.toolcards;

import com.sagrada.ppp.model.Dice;
import com.sagrada.ppp.model.DiceBag;

public class CommandToolCard11 implements CommandToolCard {

    private Dice dice2;
    private DiceBag diceBag;
    private Dice dice;

    public CommandToolCard11(DiceBag diceBag, Dice dice){
        this.diceBag = diceBag;
        this.dice = dice;
        this.dice2 = dice;
    }

    public void useCard(){
        diceBag.addDice(new Dice(dice));
        Dice ext = diceBag.extractRandomDice();
        dice.setValue(ext.getValue());
        dice.setColor(ext.getColor());
    }


}
