package com.sagrada.ppp.cards.toolcards;

import com.sagrada.ppp.model.Dice;

public class CommandToolCard1 implements CommandToolCard {

    private Dice dice;
    private int sign;

    public CommandToolCard1(Dice dice, int sign) {
        this.dice = dice;
        this.sign = sign;
    }

    /**
     * Set the new value of the dice: dice value + 1 if sign == +1, dice value - 1 if sign == -1
     */
    @Override
    public void useCard() {
        if (!((dice.getValue() == 6 && sign == +1) || (dice.getValue() == 1 && sign == -1))) {
            dice.setValue(dice.getValue() + sign);
        }
    }

}