package com.sagrada.ppp.cards.ToolCards;

import com.sagrada.ppp.Dice;
import com.sagrada.ppp.Player;
import com.sagrada.ppp.WindowPanel;

import java.util.ArrayList;

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
