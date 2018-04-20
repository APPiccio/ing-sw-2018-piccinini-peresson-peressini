package com.sagrada.ppp;

public class Cell {

    private Color color;
    private Dice diceOn;
    private Integer value;

    public Cell(Cell cell) {
        this.color = cell.getColor();
        this.value = cell.getValue();
        if (cell.hasDiceOn()) {
            this.diceOn = cell.getDiceOn();
        }
        else {
            this.diceOn = null;
        }
    }

    public Cell(Color color) {
        this.color = color;
        this.value = null;
    }

    public Cell(int value) {
        this.color = null;
        this.value = value;
    }

    public Cell() {
        this.color = null;
        this.value = null;
    }

    public boolean hasColorRestriction(){
        return color != null;
    }

    public boolean hasValueRestriction(){
        return value != null;
    }

    public boolean hasDiceOn() {
        return diceOn != null;
    }

    public Color getColor() {
        return color;
    }

    public Integer getValue() {
        return value;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Dice getDiceOn() {
        if(this.hasDiceOn()) return new Dice(diceOn);
        else return null;
    }

    public void setDiceOn(Dice diceOn) {
        this.diceOn = diceOn;
    }

    public boolean equals(Cell cell) {
        if(this.hasDiceOn()) {
            if(cell.hasDiceOn()) {
                return this.diceOn.equals(cell.getDiceOn()) && this.value == cell.getValue() && this.color == cell.getColor();
            }
            else return false;
        }
        return this.value == cell.getValue() && this.color == cell.getColor();
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("Color = " + color + "\t\t");
        string.append("Value = " + value + "\t\t");
        if (diceOn != null) {
            string.append("Dice = " + diceOn.toString() + "\n");
        }
        else string.append("\n");
        return string.toString();
    }

}