package PPP.Cards;

import PPP.*;

public abstract class ToolCard{
    private String name;
    private int ID;
    private Color color;
    private Boolean used;

    public ToolCard() {
        name = null;
        ID = 0;
        color = null;
        used = false;
    }

    protected ToolCard(String name,int id,Color color){
        this.name = name;
        this.ID = id;
        this.color = color;
        this.used = false;

    }

    public void setUsed(){
        used = true;
    }

    public int getCost(){
        return used ? StaticValues.COST_USED_TOOLCARD : StaticValues.COST_UNUSED_TOOLCARD;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    abstract Object activate(Game game) ;
}
