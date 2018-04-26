package com.sagrada.ppp.Cards.ToolCards;

import com.sagrada.ppp.Dice;
import com.sagrada.ppp.Player;
import com.sagrada.ppp.WindowPanel;


public class CommandToolCard9 implements CommandToolCard {

    private Player player;
    private int index;

    public CommandToolCard9(Player player, int index) {

        this.player = player;
        this.index = index;
    }

    @Override
    public void useCard() {
        Dice dice = player.getActiveDice();
        try {
            if (dice != null) {
                WindowPanel windowPanel = player.getPanel();
                if (!windowPanel.atLeastOneNear(index) && windowPanel.addDice(index, dice, false, false, true)) {
                    player.setActiveDice(null);
                    player.setPanel(windowPanel);
                } else {
                    throw new IllegalStateException("ERROR >>> TRYING TO USE A CARD TO PUT A DICE IN AN ILLEGAL POSITION");
                }
            } else {
                throw new IllegalStateException("ERROR >>> TRYING TO USE A CARD WITH A PLAYER WITHOUT ACTIVE DICE!");
            }
        }catch (IllegalStateException e){
            e.printStackTrace();
        }
    }
}
