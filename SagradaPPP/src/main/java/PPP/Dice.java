package PPP;

import java.util.Random;

public class Dice {

    private Color color;
    private int value;

    public Dice(Color color, int value) throws IllegalDiceValueException {
        if (value < 1 || value > 6) {
            throw new IllegalDiceValueException();
        } else {
            this.value = value;
            this.color = color;
        }
    }

    //in case we need a random value
    public Dice(Color color) throws IllegalDiceValueException {
        this(color,new Random().nextInt(6) + 1);
    }

    //in case we need a random color
    public Dice(int value) throws IllegalDiceValueException {
        this(Color.getRandomColor(),value);
    }

    //completly random dice
    public Dice() throws IllegalDiceValueException {
        this(Color.getRandomColor(),new Random().nextInt(6) + 1);
    }


    public Color getColor() {
        return color;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Color: " + color.name() + ", Value: " + value;
    }
}
