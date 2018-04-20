package com.sagrada.ppp;

import java.util.ArrayList;
import java.util.Random;

public class DiceBag {

    private ArrayList<Dice> bag;

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
        return bag.remove(new Random().nextInt(bag.size()));
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

    public int numberOfColor(Color color){
        return (int) bag.stream().filter(x -> x.getColor() == color).count();
    }

    public int numberOfValue(final int n) {
        return (int) bag.stream().filter(x -> x.getValue() == n).count();
    }

    public int numberOf(int n, Color color) {
        return (int) bag.stream().filter(x -> x.getValue() == n && x.getColor() == color).count();
    }

}