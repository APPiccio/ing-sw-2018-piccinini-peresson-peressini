package com.sagrada.ppp.model;

import java.io.Serializable;
import java.util.Random;

public class Dice implements Serializable {

    private Color color;
    private int value;

    public Dice(Dice dice) {
        this.color = dice.getColor();
        this.value = dice.getValue();
    }

    public Dice(Color color, int value) {
        try {
            if (value < 1 || value > 6) {
                throw new IllegalArgumentException("Illegal Dice, Value given: " + value + ", has to be between 1 and 6");
            }
            else {
                this.color = color;
                this.value = value;
            }
        }
        catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    //in case we need a random value
    public Dice(Color color) {
        this(color,new Random().nextInt(6) + 1);
    }

    //in case we need a random color
    public Dice(int value) {
        this(Color.getRandomColor(),value);
    }

    //completely random dice
    public Dice() {
        this(Color.getRandomColor(),new Random().nextInt(6) + 1);
    }

    public Color getColor() {
        return this.color;
    }

    public int getValue() {
        return this.value;
    }

    public void throwDice(){
        value = new Random().nextInt(6) + 1;
    }

    public boolean isSimilar(Dice dice) {
        return this.color.equals(dice.getColor()) || this.value == dice.getValue();
    }

    public void setColor(Color color){
        this.color = color;
    }

    public void setValue(int value) {
        try {
            if (value < 1 || value > 6) {
                throw new IllegalArgumentException("Illegal Dice, Value given: " + value + ", has to be between 1 and 6");
            }
            else this.value = value;
        }
        catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public void flip(){
        this.value = 7 - this.value;
    }

    @Override
    public String toString() {
        return "Color: " + color.name() + ", Value: " + value;
    }

    public boolean equals(Object object) {
        if (object == null) return false;
        if (object == this) return true;
        if (!(object instanceof Dice)) return false;
        Dice dice = (Dice) object;
        return this.color == dice.getColor() && this.value == dice.getValue();
    }

}