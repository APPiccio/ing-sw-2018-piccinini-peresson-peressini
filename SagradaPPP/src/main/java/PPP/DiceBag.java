package PPP;

import java.util.ArrayList;

public class DiceBag {


    private ArrayList<Dice> diceBag;



    public DiceBag() throws IllegalDiceValueException {
        diceBag = new ArrayList<Dice>();
        for (Color color: Color.values())
        {
            for (int i = 0; i < StaticValues.NUMBER_OF_DICES_PER_COLOR; i++){
                diceBag.add(new Dice(color));
            }

        }

    }

    public Dice getDice(int i){
        return diceBag.get(i);
    }

    public ArrayList<Dice> getDiceBag(){

        return new ArrayList<Dice>(diceBag);
    }

}
