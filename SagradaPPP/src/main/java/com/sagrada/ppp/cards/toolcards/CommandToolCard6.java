package com.sagrada.ppp.cards.toolcards;

import com.sagrada.ppp.model.Dice;

public class CommandToolCard6 implements CommandToolCard {

    private Dice dice;

    public CommandToolCard6(Dice dice) {
        this.dice = dice;
    }

    @Override
    public void useCard() {
        dice.throwDice();
    }

}