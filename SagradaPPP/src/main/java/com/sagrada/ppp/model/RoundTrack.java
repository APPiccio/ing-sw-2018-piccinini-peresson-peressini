package com.sagrada.ppp.model;

import java.io.Serializable;
import java.util.ArrayList;

public class RoundTrack implements Serializable {

    private ArrayList<ArrayList<Dice>> dicesOnTrack;
    private int rounds;
    private int currentRound;

    /**
     * Init the first dimension of the ArrayList and 10 instances of the second dimension
     */
    public RoundTrack() {
        rounds = 10;
        currentRound = 1;
        dicesOnTrack = new ArrayList<>();
        for (int i = 0; i < rounds; i++) {
            dicesOnTrack.add(new ArrayList<>());
        }
    }

    /**
     * @param round int from 1 to Rounds, index of the returned Array
     * @return      Array that contains the dices stacked on top of a round
     */
    public ArrayList<Dice> getDicesOnRound(int round) {
        ArrayList<Dice> h = new ArrayList<>();
        for (Dice dice : dicesOnTrack.get(round - 1)) {
            h.add(new Dice(dice));
        }
        return h;
    }

    /**
     * With this function you can set only Round Arrays that have already been assigned
     * @param round int from 1 to Rounds, index of the array to set
     * @param dices array that will be set in the "round" location
     */
    void setDicesOnRound(int round, ArrayList<Dice> dices) {
        dicesOnTrack.set(round - 1, dices);
    }

    /**
     * With this function you can set only Dices that have already been assigned
     * @param round int from 1 to Rounds, index of the array that needs to be modified
     * @param index index of the dice in the round Array
     * @param dice  dice to set
     */
    public void setDice(int round, int index, Dice dice) {
        dicesOnTrack.get(round - 1).set(index, dice);
    }

    /**
     * Returns a copy of the selected dice
     * @param round int from 1 to Rounds, index of the array that needs to be accessed
     * @param index index of the dice in the round Array
     * @return      Selected dice with coordinates round-1, index
     */
    public Dice getDice(int round, int index) {
        return new Dice(getDicesOnRound(round).get(index));
    }

    /**
     * @return int from 1 to 10, representing the current turn
     */
    public int getCurrentRound() {
        return currentRound;
    }

    /**
     * Set next round if currentRound is less than rounds
     * otherwise throw IllegalStateException
     */
    void nextRound() {
        if (currentRound < rounds) {
            currentRound++;
        }
        else {
            throw new IllegalStateException("Trying to increment round over its upper bound");
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            stringBuilder.append("Round: ").append(i + 1).append("\nDices:\n");
            for (Dice dice : getDicesOnRound(i + 1)) {
                stringBuilder.append("--> ").append(dice.toString()).append("\n");
            }
            stringBuilder.append("------\n");
        }
        return stringBuilder.toString();
    }

}