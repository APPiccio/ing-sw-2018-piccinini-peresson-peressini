package com.sagrada.ppp.model;

import com.sagrada.ppp.utils.StaticValues;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class represent a bag containing 90 dices,
 * 18 for each color (blue, green, purple, red, yellow)
 */
public class DiceBag {

    private ArrayList<Dice> bag;

    public DiceBag() {
        bag = new ArrayList<>();
        for (Color color : Color.values()) {
            for (int i = 0; i < StaticValues.NUMBER_OF_DICES_PER_COLOR; i++) {
                bag.add(new Dice(color));
            }
        }
    }

    public DiceBag(DiceBag diceBag) {
        this.bag = diceBag.getDiceBag();
    }

    ArrayList<Dice> getDiceBag() {
        ArrayList<Dice> diceBag = new ArrayList<>();
        for (Dice dice : bag) {
            diceBag.add(new Dice(dice));
        }
        return diceBag;
    }

    public void addDice(Dice dice) {
        bag.add(new Dice(dice.getColor()));
    }

    public int size() {
        return bag.size();
    }

    private int numberOf(int n, Color color) {
        return (int) bag.stream().filter(x -> x.getValue() == n && x.getColor() == color).count();
    }

    public Dice extractRandomDice() {
        return new Dice(bag.remove(new Random().nextInt(bag.size())));
    }

    /**
     * @param n number of dices to extract
     * @return  ArrayList of dices randomly chosen
     */
    ArrayList<Dice> extractDices(int n) {
        if (bag.size() < n || n <= 0) {
            throw new IllegalArgumentException("Cannot extract " + n + " dices");
        }
        else {
            ArrayList<Dice> dices = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                dices.add(extractRandomDice());
            }
            return dices;
        }
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof DiceBag)) return false;
        DiceBag diceBag = (DiceBag) obj;
        for (Color color : Color.values()) {
            for (int i = 1; i < 7; i++) {
                if (this.numberOf(i, color) != diceBag.numberOf(i, color)) return false;
            }
        }
        return true;
    }

}