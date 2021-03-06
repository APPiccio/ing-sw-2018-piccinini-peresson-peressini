package com.sagrada.ppp.cards.toolcards;

import com.sagrada.ppp.model.Dice;
import com.sagrada.ppp.model.RoundTrack;

public class CommandToolCard5 implements CommandToolCard {

    private Dice draftPoolDice;
    private RoundTrack roundTrack;
    private int roundIndex;
    private int diceRoundTrackIndex;

    CommandToolCard5(Dice draftPoolDice, RoundTrack roundTrack, int roundIndex, int diceRoundTrackIndex) {
        this.draftPoolDice = draftPoolDice;
        this.roundTrack = roundTrack;
        this.roundIndex = roundIndex;
        this.diceRoundTrackIndex = diceRoundTrackIndex;
    }

    /**
     * This method swaps a draftPool dice with a dice in the roundTrack
     */
    @Override
    public void useCard() {
        Dice tmpDice = roundTrack.getDice(roundIndex, diceRoundTrackIndex);
        roundTrack.setDice(roundIndex, diceRoundTrackIndex, new Dice(draftPoolDice));
        draftPoolDice.setValue(tmpDice.getValue());
        draftPoolDice.setColor(tmpDice.getColor());
    }

}