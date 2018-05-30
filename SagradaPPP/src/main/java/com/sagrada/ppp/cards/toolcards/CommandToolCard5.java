package com.sagrada.ppp.cards.toolcards;

import com.sagrada.ppp.model.Dice;
import com.sagrada.ppp.model.RoundTrack;

public class CommandToolCard5 implements CommandToolCard {

    private Dice draftPoolDice;
    private RoundTrack roundTrack;
    private int roundIndex;
    private int diceRoundTrackIndex;

    public CommandToolCard5(Dice draftPoolDice, RoundTrack roundTrack, int roundIndex, int diceRoundTrackIndex) {
        this.draftPoolDice = draftPoolDice;
        this.roundTrack = roundTrack;
        this.roundIndex = roundIndex;
        this.diceRoundTrackIndex = diceRoundTrackIndex;
    }

    @Override
    public void useCard() {
        Dice tmpDice = roundTrack.getDice(roundIndex, diceRoundTrackIndex);
        roundTrack.setDice(roundIndex, diceRoundTrackIndex, new Dice(draftPoolDice));
        draftPoolDice.setValue(tmpDice.getValue());
        draftPoolDice.setColor(tmpDice.getColor());
    }

}