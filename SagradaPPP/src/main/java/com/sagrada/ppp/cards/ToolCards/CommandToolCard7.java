package com.sagrada.ppp.cards.ToolCards;

import com.sagrada.ppp.model.Dice;

import java.util.ArrayList;

public class CommandToolCard7 implements CommandToolCard{

    private ArrayList<Dice> draftPool;

    public CommandToolCard7(ArrayList<Dice> draftPool) {
        this.draftPool = draftPool;
    }

    @Override
    public void useCard() {
        for (Dice d:draftPool) {
            d.throwDice();
        }
    }
}
