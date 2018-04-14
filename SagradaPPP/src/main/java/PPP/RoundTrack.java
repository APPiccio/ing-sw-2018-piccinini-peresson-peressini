package PPP;

import java.util.ArrayList;

public class RoundTrack {

    private ArrayList<ArrayList<Dice>> dicesOnTrack;
    private int rounds;
    private int currentRounds;

    /**
     * Init the first dimention of the ArrayList and 10 istances of the second dimention.
     */

    public RoundTrack(int rounds) {
        rounds = rounds;
        currentRounds = 0;
        dicesOnTrack = new ArrayList<>();
        for (int i = 0;i < rounds ;i++){
            dicesOnTrack.add(new ArrayList<>());
        }
    }

    public RoundTrack() {
        this(10);
    }
    public ArrayList<Dice> getDicesOnTurn(int round){
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
        return new Dice(getDicesOnTurn(round).get(index));
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

    public int dicesOnTurn(int turn){
        return getDicesOnTurn(turn).size();
    }

    public boolean hasDiceOnTurn(int turn){
        return !getDicesOnTurn(turn).isEmpty();
    }

    public int getRounds() {
        return rounds;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public int getCurrentRounds() {
        return currentRounds;
    }

    public void setCurrentRounds(int currentRounds) {
        this.currentRounds = currentRounds;
    }
}
