package PPP;

import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;

public class RoundTrackTest {

    @Test
    public static void testRoundTrack(){
        RoundTrack roundTrack = new RoundTrack();
        ArrayList<Dice> dices = new ArrayList<>();
        Dice dice;
        for (int i = 1; i <= 10 ; i++) {
            dice = new Dice();
            dices.add(dice);
            roundTrack.putDice(i,dice);
            roundTrack.putDice(i,dice);

        }
        for (int i = 1; i <= 10 ; i++) {
            assertEquals(true ,roundTrack.getTopDice(i).equals(dices.get(i - 1)));
            //TODO: Add check for dices under the top one;

        }

    }
}