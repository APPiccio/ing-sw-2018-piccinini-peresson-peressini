package com.sagrada.ppp.cards.toolcards;

import com.sagrada.ppp.model.Dice;

public class CommandToolCard5 implements CommandToolCard {

    private Dice roundTrackDice;
    private Dice draftPoolDice;

    public CommandToolCard5(Dice roundTrackDice, Dice draftPoolDice) {
        this.roundTrackDice = roundTrackDice;
        this.draftPoolDice = draftPoolDice;
    }

    @Override
    public void useCard() {
        Dice tempDice = new Dice(roundTrackDice);
        roundTrackDice.setValue(draftPoolDice.getValue());
        roundTrackDice.setColor(draftPoolDice.getColor());
        draftPoolDice.setValue(tempDice.getValue());
        draftPoolDice.setColor(tempDice.getColor());
    }

}