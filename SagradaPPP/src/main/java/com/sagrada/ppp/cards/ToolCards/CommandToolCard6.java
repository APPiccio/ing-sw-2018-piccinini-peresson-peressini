package com.sagrada.ppp.cards.ToolCards;

import com.sagrada.ppp.*;

import java.util.ArrayList;

public class CommandToolCard6 implements CommandToolCard{

    private ArrayList<Dice> draftPool;
    private Player player;
    private int index;

    public CommandToolCard6(ArrayList<Dice> draftPool, Player player, int index) {
        this.draftPool = draftPool;
        this.player = player;
        this.index = index;
    }

    @Override
    public void useCard() {
        Dice dice = draftPool.get(index);
        dice.throwDice();
        player.setActiveDice(dice);
        draftPool.remove(index);
    }
}
