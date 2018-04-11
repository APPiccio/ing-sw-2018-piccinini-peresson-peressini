package PPP;

import java.util.ArrayList;

public class RoundTrack {

    private ArrayList<ArrayList<Dice>> diceOnTrack;

    public RoundTrack() {
        diceOnTrack = new ArrayList<>();
        for (int i = 0;i <= 9;i++){
            diceOnTrack.add(new ArrayList<>());
        }
    }

    public void putDice(int turn, Dice dice){
        if (turn > 0 && turn <= 10){
            int index = turn - 1; // turn starts from 1
            if(diceOnTrack.get(index) == null){
                diceOnTrack.add(index, new ArrayList<>());
                diceOnTrack.get(index).add(dice);
            }else {
                diceOnTrack.get(index).add(dice);
            }
        }else{
            throw new IllegalArgumentException("Invalid turn: " + turn);
        }
    }

    public Dice getTopDice(int turn){
        if (turn > 0 && turn <= 10) {
            int index = turn - 1; // turn starts from 1
            if (diceOnTrack.get(index) == null) {
                return null;
            } else {
                ArrayList<Dice> diceOnThisTurn = diceOnTrack.get(index);
                return diceOnThisTurn.size() == 0 ? null : diceOnThisTurn.get(diceOnThisTurn.size() - 1); // inline if that decides to return null if the sub-array.size is 0
            }
        }else{
            throw new IllegalArgumentException("Invalid turn: " + turn);
        }
    }
}
