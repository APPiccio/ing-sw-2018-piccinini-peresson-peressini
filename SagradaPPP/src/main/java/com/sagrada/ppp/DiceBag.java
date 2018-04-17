package com.sagrada.ppp;

import java.util.ArrayList;

public class DiceBag {


    private ArrayList<Dice> diceBag;


    public DiceBag() {
        diceBag = new ArrayList<Dice>();
        for (Color color: Color.values())
        {
            for (int i = 0; i < StaticValues.NUMBER_OF_DICES_PER_COLOR; i++){
                diceBag.add(new Dice(color));
            }

        }

    }

    public int size(){
        return diceBag.size();
    }

    public Dice getDice(int i){
        return diceBag.get(i);
    }

    public ArrayList<Dice> getDiceBag(){

        return new ArrayList<Dice>(diceBag);
    }

    public int numberOfValue (final int n){
        return (int) diceBag.stream().filter(x -> x.getValue() == n).count();
    }

    public int numberOfColor (Color color){
        return (int) diceBag.stream().filter(x -> x.getColor() == color).count();
    }

    public int numberOf (int n, Color color){
        return (int) diceBag.stream().filter(x -> x.getValue() == n && x.getColor() == color).count();
    }


}
