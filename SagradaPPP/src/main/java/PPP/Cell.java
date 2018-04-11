package PPP;

public class Cell {
    private Color color;
    private Integer value;
    private Dice diceOn;

    public Cell(Cell cell){
        this.color = cell.getColor();
        this.value = cell.getValue();
        this.diceOn = cell.getDiceOn();
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

    public Boolean hasValueRestriction(){
        return value != null;
    }

    public Boolean hasColorRestriction(){
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
        if(this.hasDiceon()) return new Dice(diceOn);
        return null;
    }

    public void setDiceOn(Dice diceOn) {
        this.diceOn = diceOn;
    }//TODO implement color and value constraints

    public Boolean hasDiceon(){
        return diceOn != null;
    }

    public Object equals(Cell cell) {
        if(this.hasDiceon()){
            if(cell.hasDiceon()){
                return this.diceOn.equals(cell.getDiceOn()) && this.value == cell.getValue() && this.color == cell.getColor();
            }
            else return false;
        }
        return this.value == cell.getValue() && this.color == cell.getColor();
    }
}
