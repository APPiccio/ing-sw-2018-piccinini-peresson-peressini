package PPP;

import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;

public class RoundTrackTest {

    @Test
    public static void testRoundTrack(){
        RoundTrack roundTrack = new RoundTrack();
        ArrayList<Dice> firtsRowDices = new ArrayList<>();
        ArrayList<Dice> secondRowDices = new ArrayList<>();
        Dice dice1,dice2;
        for (int i = 0; i <= 9; i++){
            dice1 = new Dice();
            dice2 = new Dice();
            firtsRowDices.add(dice1);
            secondRowDices.add(dice2);
            roundTrack.addDice(i + 1,dice1);
            roundTrack.addDice(i + 1,dice2);
        }
        roundTrack.removeDice(5,roundTrack.dicesOnRound(2)-1);
        secondRowDices.set(4,null);

        for (int i = 0; i <= 9; i++){

            if(secondRowDices.get(i) != null) {
                assertEquals(true,roundTrack.getDice(i + 1, roundTrack.dicesOnRound(i + 1) - 1).equals(secondRowDices.get(i)));
            }else {
                assertEquals(true,roundTrack.getDice(i + 1, roundTrack.dicesOnRound(i + 1) - 1).equals(firtsRowDices.get(i)));
            }

        }
    }
}