package com.sagrada.ppp.cards.ToolCards;

import com.sagrada.ppp.model.Dice;
import com.sagrada.ppp.model.Player;

public class CommandToolCard8 implements CommandToolCard {

    private Player player;
    private Dice dice;

    public CommandToolCard8(Player player, Dice dice) {
        this.player =player;
        this.dice = dice;
    }

    @Override
    public void useCard() {
        player.setActiveDice(dice);
    }
}
