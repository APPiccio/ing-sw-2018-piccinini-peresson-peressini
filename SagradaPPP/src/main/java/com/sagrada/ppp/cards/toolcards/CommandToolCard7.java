package com.sagrada.ppp.cards.toolcards;

import com.sagrada.ppp.model.Dice;

import java.util.ArrayList;

public class CommandToolCard7 implements CommandToolCard{

    private ArrayList<Dice> draftPool;

    public CommandToolCard7(ArrayList<Dice> draftPool) {
        this.draftPool = draftPool;
    }

    /**
     * Re-roll all dices in draftPool
     */
    @Override
    public void useCard() {
        for (Dice d : draftPool) {
            d.throwDice();
        }
    }

}