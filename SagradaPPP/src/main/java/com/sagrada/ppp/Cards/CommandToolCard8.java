package com.sagrada.ppp.Cards;

import com.sagrada.ppp.Dice;
import com.sagrada.ppp.Player;

import java.util.ArrayList;

public class CommandToolCard8 implements CommandToolCard {

    private ArrayList<Dice> draftPool;
    private Player player;
    private int index;

    public CommandToolCard8(ArrayList<Dice> draftPool, Player player, int index) {
        this.draftPool = draftPool;
        this.player = player;
        this.index = index;
    }

    @Override
    public void useCard() {
        player.setActiveDice(draftPool.get(index));
    }
}
