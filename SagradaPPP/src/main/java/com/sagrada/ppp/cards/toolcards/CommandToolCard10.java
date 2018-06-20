package com.sagrada.ppp.cards.toolcards;

import com.sagrada.ppp.model.Dice;

public class CommandToolCard10 implements CommandToolCard {

    private Dice dice;

    public CommandToolCard10(Dice dice) {
        this.dice = dice;
    }

    /**
     * This method flips this.dice to its opposite side
     */
    @Override
    public void useCard() {
        dice.setValue(7 - dice.getValue());
    }

}
