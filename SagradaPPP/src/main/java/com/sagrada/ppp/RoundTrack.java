package com.sagrada.ppp;

import java.util.ArrayList;

public class RoundTrack {

    private ArrayList<ArrayList<Dice>> dicesOnTrack;
    private int rounds;
    private int currentRound;

    /**
     * Init the first dimention of the ArrayList and 10 istances of the second dimention.
     * @param rounds sets the number of rounds!
     */

    public RoundTrack(int rounds) {
        this.rounds = rounds;
        currentRound = 1;
        dicesOnTrack = new ArrayList<>();
        for (int i = 0;i < rounds ;i++){
            dicesOnTrack.add(new ArrayList<>());
        }
    }

    /**
     * Default constructor, sets rounds to 10!
     */
    public RoundTrack() {
        this(10);
    }

    /**
     * @param roundTrack RoundTrack to be copied. Creates a new RoundTrack instance equal to the one given
     */
    public RoundTrack(RoundTrack roundTrack) {
        this.rounds = roundTrack.rounds;
        this.currentRound = roundTrack.currentRound;
        this.dicesOnTrack = new ArrayList<>(roundTrack.dicesOnTrack);
    }

    /**
     * @param round int from 1 to Rounds, index of the returned Array
     * @return Array that contains the dices stacked on top of a round.
     */
    public ArrayList<Dice> getDicesOnRound(int round){
        ArrayList<Dice> h = new ArrayList<>();
        for(Dice dice : dicesOnTrack.get(round - 1)){
            h.add(new Dice(dice));
        }
        return h;
    }

    /**
     * With this function you can set only Round Arrays that have already been assigned.
     * @param round int from 1 to Rounds, index of the array to set.
     * @param dices array that will be set in the "round" location
     */
    public void setDicesOnTurn(int round , ArrayList<Dice> dices){
        dicesOnTrack.set(round - 1,dices);
    }

    /**
     * With this function you can set only Dices that have already been assigned.
     * @param round int from 1 to Rounds, index of the array that needs to be modified.
     * @param index index of the dice in the round Array
     * @param dice dice to set.
     */
    public void setDice(int round, int index, Dice dice){
        dicesOnTrack.get(round - 1).set(index,dice);

    }

    /**
     * Adds a dice on the tail of the list
     * @param round int from 1 to Rounds, index of the array that needs to be modified.
     * @param dice dice to set.
     */
    public void addDice(int round, Dice dice){
        dicesOnTrack.get(round - 1).add(dice);
    }

    /**
     * Returns a copy of the selected dice
     * @param round int from 1 to Rounds, index of the array that needs to be accessed.
     * @param index index of the dice in the round Array
     * @return Selected dice with coordinates round-1,index
     */
    public Dice getDice(int round, int index){
        return new Dice(getDicesOnRound(round).get(index));
    }


    /**
     * @param round int from 1 to Rounds, index of the array that needs to be modified.
     * @param index index of the dice in the round Array
     * @return Returns true if dice is present, false in every other case.
     */
    public boolean removeDice(int round,int index){
        ArrayList<Dice> dices = dicesOnTrack.get(round-1);
        if(dices.isEmpty()){
            return false;
        }else {
            dices.remove(index);
            return true;
        }
    }

    /**
     * @param round int from 1 to Rounds, index of the array that needs to be accessed.
     * @return number of dices on the given round
     */
    public int dicesOnRound(int round){
        return getDicesOnRound(round).size();
    }

    /**
     * @param round int from 1 to Rounds, index of the array that needs to be accessed.
     * @return returns false if there are no dices on the given round. true with n>0 of dices.
     */
    public boolean hasDiceOnRound(int round){
        return !getDicesOnRound(round).isEmpty();
    }

    /**
     * @return returns number of rounds. Normally 10.
     */
    public int getRounds() {
        return rounds;
    }

    /**
     * @return from 1
     */
    public int getCurrentRound() {
        return currentRound;
    }

    public void setCurrentRound(int currentRound) {
        this.currentRound = currentRound;
    }

    public void nextRound(){
        if(currentRound < rounds){
            currentRound++;
        }else {
            throw new IllegalStateException("trying to increment round over its upper bound");
        }
    }
}
