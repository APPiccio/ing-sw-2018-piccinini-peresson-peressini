package com.sagrada.ppp.cards.toolcards;

import com.sagrada.ppp.model.Dice;
import com.sagrada.ppp.model.Player;
import com.sagrada.ppp.model.WindowPanel;

public class CommandToolCard9 implements CommandToolCard {

    private Player player;
    private int panelIndex;
    private Dice draftPoolDice;

    CommandToolCard9(Player player, int panelIndex, Dice draftPoolDice) {
        this.player = player;
        this.panelIndex = panelIndex;
        this.draftPoolDice = draftPoolDice;
    }

    /**
     * This method places a draftPool dice in player's windowPanel, ignoring positioning restriction
     */
    @Override
    public void useCard() {
        WindowPanel panel = player.getPanel();
        if (panel.noDiceNear(panelIndex)) {
            panel.addDice(panelIndex, draftPoolDice, false, false, true);
            player.setPanel(panel);
        }
    }

}
