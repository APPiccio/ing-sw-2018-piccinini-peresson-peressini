package com.sagrada.ppp.Cards;

import com.sagrada.ppp.Dice;
import com.sagrada.ppp.DiceBag;

import java.util.ArrayList;

public class CommandToolCard11 implements CommandToolCard {

    private DiceBag diceBag;
    private Dice dice;

    public CommandToolCard11(DiceBag diceBag, Dice dice){
        this.diceBag = diceBag;
        this.dice = dice;
    }

    public void useCard(){
        diceBag.addDice(dice);
        dice = diceBag.extractRandomDice();
    }


}
