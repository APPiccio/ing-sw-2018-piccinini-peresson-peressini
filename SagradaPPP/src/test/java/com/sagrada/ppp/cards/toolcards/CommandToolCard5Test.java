package com.sagrada.ppp.cards.toolcards;

import com.sagrada.ppp.model.Color;
import com.sagrada.ppp.model.Dice;
import com.sagrada.ppp.model.RoundTrack;
import org.junit.*;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.*;

public class CommandToolCard5Test {

    private ToolCard toolCard5;

    @Before
    public void setUp() {
        toolCard5 = new ToolCard5();
    }

    @Test
    public void useCard() {
        RoundTrack roundTrack = new RoundTrack();

        //Creating roundTrack: supposed to be at the seventh round, 4 players participating at the game
        for (int round = 1; round <= 7; round++) { //i indicate the round number
            ArrayList<Dice> dices = new ArrayList<>();
            Random rand = new Random();
            int remainingDices = rand.nextInt(9) + 1; //random number between 1 and 9
            for (int i = 0; i < remainingDices; i++) {
                dices.add(new Dice(Color.PURPLE, 5));
            }
            roundTrack.setDicesOnRound(round, dices);
        }

        ArrayList<Dice> draftPool = new ArrayList<>(); //extracted dices
        for (int i = 0; i < 9; i++) {
            draftPool.add(new Dice(Color.GREEN, 2));
        }

        //Getting the two dices that will be swapped
        int draftPoolDiceIndex = new Random().nextInt(draftPool.size());
        Dice draftPoolDice = draftPool.get(draftPoolDiceIndex);
        int round = new Random().nextInt(7) + 1;
        int diceRoundTrackIndex = new Random().nextInt(roundTrack.getDicesOnRound(round).size());
        Dice roundTrackDice = roundTrack.getDice(round, diceRoundTrackIndex);

        Dice oldDraftPoolDice = new Dice(draftPoolDice);
        Dice oldRoundTrackDice = new Dice(roundTrackDice);

        toolCard5.use(new CommandToolCard5(draftPoolDice, roundTrack, round, diceRoundTrackIndex));
        roundTrackDice = new Dice(roundTrack.getDice(round, diceRoundTrackIndex));

        assertNotEquals(oldDraftPoolDice, draftPoolDice);
        assertNotEquals(oldRoundTrackDice, roundTrackDice);
        assertEquals(oldDraftPoolDice, roundTrackDice);
        assertEquals(oldRoundTrackDice, draftPoolDice);

        assertEquals(Color.GREEN, roundTrackDice.getColor());
        assertEquals(2, roundTrackDice.getValue());
        assertEquals(Color.PURPLE, draftPoolDice.getColor());
        assertEquals(5, draftPoolDice.getValue());
    }

}