package com.sagrada.ppp.cards.toolcards;

import com.sagrada.ppp.model.Dice;
import com.sagrada.ppp.model.DiceBag;

public class CommandToolCard11 implements CommandToolCard {

    private DiceBag diceBag;
    private Dice dice;

    public CommandToolCard11(DiceBag diceBag, Dice dice) {
        this.diceBag = diceBag;
        this.dice = dice;
    }

    /**
     * This method returns this.dice to the draftPool and extracts a new one
     */
    @Override
    public void useCard() {
        diceBag.addDice(new Dice(dice));
        Dice ext = diceBag.extractRandomDice();
        dice.setValue(ext.getValue());
        dice.setColor(ext.getColor());
    }

}