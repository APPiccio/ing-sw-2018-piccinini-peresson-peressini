package com.sagrada.ppp.model;

import com.sagrada.ppp.utils.StaticValues;
import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class RoundTrackTest {

    private ArrayList<Dice> dices;
    private RoundTrack roundTrack;

    @Before
    public void setUp() {
        dices = new ArrayList<>();
        dices.add(new Dice(Color.GREEN, 1));
        dices.add(new Dice(Color.RED, 2));
        dices.add(new Dice(Color.PURPLE, 3));

        roundTrack = new RoundTrack();
        for (int round = 1; round <= StaticValues.NUMBER_OF_ROUNDS; round++) {
            ArrayList<Dice> dices = new ArrayList<>();
            for (int i = 0; i < 9; i++) {
                dices.add(new Dice());
            }
            roundTrack.setDicesOnRound(round, dices);
        }
    }

    @Test
    public void setDicesOnRound() {
        setUp();
        for (int round = 1; round <= StaticValues.NUMBER_OF_ROUNDS; round++) {
            assertEquals(9, roundTrack.getDicesOnRound(round).size());
        }

        roundTrack.setDicesOnRound(1, dices);

        assertEquals(Color.GREEN, roundTrack.getDicesOnRound(1).get(0).getColor());
        assertEquals(1, roundTrack.getDicesOnRound(1).get(0).getValue());
        assertEquals(Color.RED, roundTrack.getDicesOnRound(1).get(1).getColor());
        assertEquals(2, roundTrack.getDicesOnRound(1).get(1).getValue());
        assertEquals(Color.PURPLE, roundTrack.getDicesOnRound(1).get(2).getColor());
        assertEquals(3, roundTrack.getDicesOnRound(1).get(2).getValue());
        assertNotEquals(Color.YELLOW, roundTrack.getDicesOnRound(1).get(0).getColor());
        assertNotEquals(6, roundTrack.getDicesOnRound(1).get(0).getValue());
    }

    @Test
    public void getDice() {
        setUp();
        roundTrack.setDicesOnRound(1, dices);

        Dice dice = roundTrack.getDice(1, 0);
        assertEquals(Color.GREEN, dice.getColor());
        assertEquals(1, dice.getValue());

        dice = roundTrack.getDice(1, 1);
        assertEquals(Color.RED, dice.getColor());
        assertEquals(2, dice.getValue());

        dice = roundTrack.getDice(1, 2);
        assertEquals(Color.PURPLE, dice.getColor());
        assertEquals(3, dice.getValue());
    }

    @Test
    public void setDice() {
        setUp();
        assertNotNull(roundTrack.getDice(1, 0));
        assertNotNull(roundTrack.getDice(3, 4));
        assertNotNull(roundTrack.getDice(7, 7));
        assertNotNull(roundTrack.getDice(10, 8));

        assertEquals(9, roundTrack.getDicesOnRound(1).size());

        roundTrack.setDicesOnRound(1, dices);

        assertEquals(3, roundTrack.getDicesOnRound(1).size());

        assertEquals(Color.GREEN, roundTrack.getDicesOnRound(1).get(0).getColor());
        assertEquals(1, roundTrack.getDicesOnRound(1).get(0).getValue());

        roundTrack.setDice(1, 1, new Dice(Color.BLUE, 5));
        assertNotEquals(Color.RED, roundTrack.getDicesOnRound(1).get(1).getColor());
        assertNotEquals(2, roundTrack.getDicesOnRound(1).get(1).getValue());
        assertEquals(Color.BLUE, roundTrack.getDicesOnRound(1).get(1).getColor());
        assertEquals(5, roundTrack.getDicesOnRound(1).get(1).getValue());
    }

    @Test
    public void getDicesOnRound() {
        setUp();
        roundTrack.setDicesOnRound(1, dices);

        ArrayList<Dice> dicesOnRound = roundTrack.getDicesOnRound(1);

        assertEquals(dices, dicesOnRound);
        assertNotEquals(dices.hashCode(), dicesOnRound.hashCode());
        for (int i = 0; i < roundTrack.getDicesOnRound(1).size(); i++) {
            assertEquals(dices.get(i), roundTrack.getDice(1, i));
            assertNotEquals(dices.get(i).hashCode(), roundTrack.getDice(1, i).hashCode());
        }
    }

    @Test
    public void getCurrentRound_nextRound() {
        setUp();
        assertEquals(1, roundTrack.getCurrentRound());

        roundTrack.nextRound();
        roundTrack.nextRound();

        assertEquals(3, roundTrack.getCurrentRound());
    }

    @Test
    public void toStringTest() {
        RoundTrack roundTrack = new RoundTrack();
        roundTrack.setDicesOnRound(1, dices);

        String string = "Round: 1\nDices:\n" +
                "--> " + dices.get(0).toString() + "\n" +
                "--> " + dices.get(1).toString() + "\n" +
                "--> " + dices.get(2).toString() + "\n" +
                "------\n" +
                "Round: 2\nDices:\n" +
                "------\n" +
                "Round: 3\nDices:\n" +
                "------\n" +
                "Round: 4\nDices:\n" +
                "------\n" +
                "Round: 5\nDices:\n" +
                "------\n" +
                "Round: 6\nDices:\n" +
                "------\n" +
                "Round: 7\nDices:\n" +
                "------\n" +
                "Round: 8\nDices:\n" +
                "------\n" +
                "Round: 9\nDices:\n" +
                "------\n" +
                "Round: 10\nDices:\n" +
                "------\n";

        assertEquals(string, roundTrack.toString());
    }

    @Test(expected = IllegalStateException.class)
    public void nextRoundException() {
        setUp();
        for (int i = 2; i <= StaticValues.NUMBER_OF_ROUNDS + 1; i++) {
            roundTrack.nextRound();
        }
    }

}