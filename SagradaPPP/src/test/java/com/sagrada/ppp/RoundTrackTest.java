package com.sagrada.ppp;

import com.sagrada.ppp.model.Color;
import com.sagrada.ppp.model.Dice;
import com.sagrada.ppp.model.RoundTrack;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;

public class RoundTrackTest {

    public RoundTrackTest() {
        testGetters();
        testRemove();
        testRoundTrack();
        testRoundTrackBehavior();
        testSetDiceMethods();
    }

    //testing constructor
    @Test
    public void testRoundTrack(){
        RoundTrack roundTrack = new RoundTrack();
        RoundTrack roundTrack1 = new RoundTrack(roundTrack);
        assertEquals(false, roundTrack.hashCode() == roundTrack1.hashCode());

    }

    @Test
    public void testRoundTrackBehavior(){
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

    //test remove and hasDiceOnRound
    @Test
    public void testRemove(){
        RoundTrack roundTrack = new RoundTrack();

        //removing non existing dice
        assertEquals(false,roundTrack.removeDice(1,0));
        assertEquals(0,roundTrack.dicesOnRound(1));


        roundTrack.addDice(1,new Dice());
        //test removing first dice
        assertEquals(true,roundTrack.removeDice(1,0));
        assertEquals(0,roundTrack.dicesOnRound(1));
        assertEquals(false,roundTrack.hasDiceOnRound(1));

        roundTrack.addDice(1,new Dice());
        roundTrack.addDice(1,new Dice());
        roundTrack.addDice(1,new Dice());
        //test removing dice n
        assertEquals(true,roundTrack.removeDice(1,1));
        assertEquals(2,roundTrack.dicesOnRound(1));

    }

    //testing setDice setDicesOnTurn and addDice
    @Test
    public void testSetDiceMethods(){
        ArrayList<Dice> dices = new ArrayList<>();
        RoundTrack roundTrack1 = new RoundTrack();
        RoundTrack roundTrack2 = new RoundTrack();


        for (int i = 0;i < 40; i++){
            Dice dice = new Dice();
            dices.add(dice);
            roundTrack1.addDice(10,dice);

        }
        roundTrack2.setDicesOnTurn(5,dices);


        assertEquals(true, dices.equals(roundTrack1.getDicesOnRound(10)));
        assertEquals(true, dices.equals(roundTrack2.getDicesOnRound(5)));

        roundTrack1.setDice(10,35,new Dice(Color.GREEN,2));
        assertEquals(true,roundTrack1.getDice(10,35).equals(new Dice(Color.GREEN,2)));


    }


    @Test
    public void testGetters(){
        ArrayList<Dice> dices = new ArrayList<>();
        RoundTrack roundTrack1 = new RoundTrack();
        RoundTrack roundTrack2 = new RoundTrack();


        for (int i = 0;i < 40; i++){
            Dice dice = new Dice();
            dices.add(dice);
            roundTrack2.addDice(1,dice);
            assertEquals(true,roundTrack2.getDice(1,i).equals(dice));
            assertEquals(false,roundTrack2.getDice(1,i).hashCode() == dice.hashCode());

        }
        roundTrack1.setDicesOnTurn(1,dices);
        assertEquals(true,roundTrack1.getDicesOnRound(1).equals(dices));
        assertEquals(false,roundTrack1.getDicesOnRound(1).hashCode() == dices.hashCode());

        assertEquals(true,roundTrack1.getRounds() == 10);
        assertEquals(true, roundTrack1.getCurrentRound() == 1);
        roundTrack1.nextRound();
        assertEquals(true, roundTrack1.getCurrentRound() == 2);


    }
}