package com.sagrada.ppp;

import java.util.ArrayList;

public class RoundTrack {

    private ArrayList<ArrayList<Dice>> dicesOnTrack;
    private int rounds;
    private int currentRound;

    /**
     * Init the first dimention of the ArrayList and 10 istances of the second dimention.
     */

    public RoundTrack(int rounds) {
        this.rounds = rounds;
        currentRound = 1;
        dicesOnTrack = new ArrayList<>();
        for (int i = 0;i < rounds ;i++){
            dicesOnTrack.add(new ArrayList<>());
        }
    }

    public RoundTrack() {
        this(10);
    }

    public RoundTrack(RoundTrack roundTrack) {
        this.rounds = roundTrack.rounds;
        this.currentRound = roundTrack.currentRound;
        this.dicesOnTrack = new ArrayList<>(roundTrack.dicesOnTrack);
    }
    public ArrayList<Dice> getDicesOnRound(int round){
        return new ArrayList<>(dicesOnTrack.get(round - 1));
    }
    public void setDicesOnTurn(int round , ArrayList<Dice> dices){
        dicesOnTrack.set(round - 1,dices);
    }

    public void setDice(int round, int index, Dice dice){
        dicesOnTrack.get(round - 1).set(index,dice);

    }

    public void addDice(int round, Dice dice){
        dicesOnTrack.get(round - 1).add(dice);
    }

    public Dice getDice(int round, int index){
        return new Dice(getDicesOnRound(round).get(index));
    }


    public boolean removeDice(int round,int index){
        ArrayList<Dice> dices = dicesOnTrack.get(round-1);
        if(dices.isEmpty()){
            return false;
        }else {
            dices.remove(index);
            return true;
        }
    }

    public int dicesOnRound(int turn){
        return getDicesOnRound(turn).size();
    }

    public boolean hasDiceOnRound(int turn){
        return !getDicesOnRound(turn).isEmpty();
    }

    public int getRounds() {
        return rounds;
    }

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
