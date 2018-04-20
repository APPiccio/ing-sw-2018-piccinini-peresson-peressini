package com.sagrada.ppp;

public class Cell {

    private Color color;
    private Integer value;
    private Dice diceOn;

    public Cell(Cell cell){
        this.color = cell.getColor();
        this.value = cell.getValue();
        if (cell.hasDiceOn()) {
            this.diceOn = cell.getDiceOn();
        }
        else{
            this.diceOn = null;
        }
    }

    public Cell(Color color){
        this.color = color;
        value = null;
    }

    public Cell(int value){
        this.value = value;
        color = null;
    }

    public Cell(){
        color = null;
        value = null;
    }

    public boolean hasValueRestriction(){
        return value != null;
    }

    public boolean hasColorRestriction(){
        return color != null;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Dice getDiceOn() {
        if(this.hasDiceOn()) return new Dice(diceOn);
        return null;
    }

    public void setDiceOn(Dice diceOn) {
        this.diceOn = diceOn;
    }

    public boolean hasDiceOn(){
        return diceOn != null;
    }

    public boolean equals(Object object) {
        if (object == null) return false;
        if (object == this) return true;
        if (!(object instanceof Cell))return false;
        Cell cell = (Cell) object;
        if(this.hasDiceOn()){
            if(cell.hasDiceOn()){
                return this.diceOn.equals(cell.getDiceOn()) && this.value == cell.getValue() && this.color == cell.getColor();
            }
            else return false;
        }
        return this.value == cell.getValue() && this.color == cell.getColor();
    }

    @Override
    public String toString(){
        StringBuilder string = new StringBuilder();
        string.append("Color = " + color + "\t\t");
        string.append("Value = " + value + "\t\t");
        if (diceOn != null) {
            string.append("Dice = " + diceOn.toString() + "\n");
        }
        else{
            string.append("\n");
        }
        return string.toString();
    }
}
