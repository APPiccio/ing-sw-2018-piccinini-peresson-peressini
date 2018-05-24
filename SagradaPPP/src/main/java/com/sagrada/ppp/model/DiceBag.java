package com.sagrada.ppp.model;

import com.sagrada.ppp.utils.IllegalDiceExtractionException;
import com.sagrada.ppp.utils.StaticValues;

import java.util.ArrayList;
import java.util.Random;

public class DiceBag {

    private ArrayList<Dice> bag;

    public DiceBag(DiceBag diceBag){
        this.bag = diceBag.getDiceBag();
    }


    public DiceBag() {
        bag = new ArrayList<>();
        for (Color color: Color.values()) {
            for (int i = 0; i < StaticValues.NUMBER_OF_DICES_PER_COLOR; i++) {
                bag.add(new Dice(color)); //Dices with random values
            }
        }
    }

    public int size(){
        return bag.size();
    }

    public Dice getDice(int i){
        return bag.get(i);
    }

    public Dice extractRandomDice() {
        return new Dice(bag.remove(new Random().nextInt(bag.size())));
    }

    public ArrayList<Dice> getDiceBag() {
        return new ArrayList<>(bag);
    }

    public ArrayList<Dice> extractDices(int n) {
        try {
            if (bag.size() < n || n <= 0) {
                throw new IllegalDiceExtractionException(n);
            }
            else {
                ArrayList<Dice> dices = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    dices.add(extractRandomDice());
                }
                return dices;
            }
        }
        catch (IllegalDiceExtractionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void addDice(Dice dice){
        bag.add(new Dice(dice.getColor()));
    }

    public int numberOfColor(Color color){
        return (int) bag.stream().filter(x -> x.getColor() == color).count();
    }

    public int numberOfValue(final int n) {
        return (int) bag.stream().filter(x -> x.getValue() == n).count();
    }

    public int numberOf(int n, Color color) {
        return (int) bag.stream().filter(x -> x.getValue() == n && x.getColor() == color).count();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof DiceBag)) return false;
        DiceBag diceBag = (DiceBag) obj;
        for(Color color : Color.values()){
            for(int i = 1; i < 7; i++){
                if(this.numberOf(i,color) != diceBag.numberOf(i,color)) return false;
            }
        }
        return true;
    }
}