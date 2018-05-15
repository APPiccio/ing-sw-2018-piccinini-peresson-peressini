package com.sagrada.ppp.cards.ToolCards;

import com.sagrada.ppp.*;

import java.util.ArrayList;

public class CommandToolCard6 implements CommandToolCard{

    private Dice dice;
    private Player player;
    private int draftPoolIndex;

    public CommandToolCard6(Dice dice, Player player) {
        this.dice = dice;
        this.player = player;
    }

    @Override
    public void useCard() {
        dice.throwDice();
        player.setActiveDice(dice);
    }
}
