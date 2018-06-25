package com.sagrada.ppp.cards.toolcards;

import com.sagrada.ppp.cards.ToolCardParameterContainer;
import com.sagrada.ppp.model.*;
import com.sagrada.ppp.model.Color;
import org.junit.*;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.*;

public class CommandToolCard5Test {

    private ToolCard toolCard5;
    private ToolCardParameterContainer container;
    private Player player;

    @Before
    public void setUp() {
        toolCard5 = new ToolCard5();
        container = new ToolCardParameterContainer();
        container.toolCardParameters = new ToolCardParameters();
        player = new Player("ciao");
        player.setPanel( new WindowPanel(1, 0));
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

        container.roundTrack = roundTrack;
        container.player = player;
        container.draftPool = draftPool;
        container.toolCardParameters.roundTrackRoundIndex = round;
        container.toolCardParameters.roundTrackDiceIndex = diceRoundTrackIndex;
        container.toolCardParameters.draftPoolDiceIndex = draftPoolDiceIndex;

        assertTrue(toolCard5.paramsOk(container));
        toolCard5.use(container);
        roundTrackDice = new Dice(roundTrack.getDice(round, diceRoundTrackIndex));

        assertNotEquals(oldDraftPoolDice, draftPoolDice);
        assertNotEquals(oldRoundTrackDice, roundTrackDice);
        assertEquals(oldDraftPoolDice, roundTrackDice);
        assertEquals(oldRoundTrackDice, draftPoolDice);

        assertEquals(Color.GREEN, roundTrackDice.getColor());
        assertEquals(2, roundTrackDice.getValue());
        assertEquals(Color.PURPLE, draftPoolDice.getColor());
        assertEquals(5, draftPoolDice.getValue());

        //Testing paramsOk
        container.roundTrack = roundTrack;
        container.player = player;
        container.draftPool = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            container.draftPool.add(null);
        }
        container.toolCardParameters.roundTrackRoundIndex = round;
        container.toolCardParameters.roundTrackDiceIndex = diceRoundTrackIndex;
        container.toolCardParameters.draftPoolDiceIndex = draftPoolDiceIndex;
        assertFalse(toolCard5.paramsOk(container));
    }

}