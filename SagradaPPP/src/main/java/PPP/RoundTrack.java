package PPP;

import java.util.ArrayList;

public class RoundTrack {

    private ArrayList<ArrayList<Dice>> dicesOnTrack;

    /**
     * Init the first dimention of the ArrayList and 10 istances of the second dimention.
     */
    public RoundTrack(int turns) {
        dicesOnTrack = new ArrayList<>();
        for (int i = 0;i < turns ;i++){
            dicesOnTrack.add(new ArrayList<>());
        }
    }

    public RoundTrack() {
        this(10);
    }
    public ArrayList<Dice> getDicesOnTurn(int turn){
        return new ArrayList<>(dicesOnTrack.get(turn - 1));
    }
    public void setDicesOnTurn(int turn, ArrayList<Dice> dices){
        dicesOnTrack.set(turn-1,dices);
    }

    public void setDice(int turn, int index, Dice dice){
        dicesOnTrack.get(turn-1).set(index,dice);

    }

    public void addDice(int turn, Dice dice){
        dicesOnTrack.get(turn - 1).add(dice);
    }

    public Dice getDice(int turn, int index){
        return new Dice(getDicesOnTurn(turn).get(index));
    }


    public boolean removeDice(int turn,int index){
        ArrayList<Dice> dices = dicesOnTrack.get(turn-1);
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

}
