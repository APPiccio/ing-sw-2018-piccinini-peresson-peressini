package com.sagrada.ppp.model;

import java.io.Serializable;
import java.util.Random;

/**
 * This class represents a six-faced dice
 */
public class Dice implements Serializable {

    private Color color;
    private int value;

    public Dice(Dice dice) {
        this.color = dice.getColor();
        this.value = dice.getValue();
    }

    public Dice(Color color, int value) {
        if (value < 1 || value > 6) {
            throw new IllegalArgumentException("Illegal dice! Value given: " + value +
                    "; it has to be between 1 and 6");
        }
        else {
            this.color = color;
            this.value = value;
        }
    }

    /**
     * Create a dice with the given color and a random value
     * @param color color of the dice
     */
    public Dice(Color color) {
        this(color, new Random().nextInt(6) + 1);
    }

    /**
     * Create a dice with the given value and a random color
     * @param value value of the dice
     */
    public Dice(int value) {
        this(Color.getRandomColor(), value);
    }

    /**
     * Create a completely random dice
     */
    public Dice() {
        this(Color.getRandomColor(), new Random().nextInt(6) + 1);
    }

    /**
     * Randomly choose another value for this dice
     */
    public void throwDice() {
        value = new Random().nextInt(6) + 1;
    }

    /**
     * @param dice  dice to compare
     * @return      true if this and dice have the same value or color
     */
    boolean isSimilar(Dice dice) {
        return this.color.equals(dice.getColor()) || this.value == dice.getValue();
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        if (value < 1 || value > 6) {
            throw new IllegalArgumentException("Illegal dice! Value given: " + value +
                    "; it has to be between 1 and 6");
        }
        else this.value = value;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) return false;
        if (object == this) return true;
        if (!(object instanceof Dice)) return false;
        Dice dice = (Dice) object;
        return this.color == dice.getColor() && this.value == dice.getValue();
    }

    @Override
    public String toString() {
        return "Color: " + color.name() + ", value: " + value;
    }

}