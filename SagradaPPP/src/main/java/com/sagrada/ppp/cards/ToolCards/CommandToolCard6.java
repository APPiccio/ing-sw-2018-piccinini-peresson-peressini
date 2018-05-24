package com.sagrada.ppp.cards.ToolCards;

import com.sagrada.ppp.model.Dice;
import com.sagrada.ppp.model.Player;

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
