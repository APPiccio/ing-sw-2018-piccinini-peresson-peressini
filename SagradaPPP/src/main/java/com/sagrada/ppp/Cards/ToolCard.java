package com.sagrada.ppp.Cards;

import com.sagrada.ppp.Color;
import com.sagrada.ppp.Game;
import com.sagrada.ppp.StaticValues;
import com.sagrada.ppp.WindowPanel;

public abstract class ToolCard{

    private String name;
    private int id;
    private Color color;
    private boolean used;

    public ToolCard() {
        name = null;
        id = 0;
        color = null;
        used = false;
    }

    protected ToolCard(String name,int id,Color color) {
        this.name = name;
        this.id = id;
        this.color = color;
        this.used = false;
    }

    public WindowPanel use(CommandToolCard commandToolCard){
        setUsed();
        return commandToolCard.useCard();
    }

    public void setUsed() {
        used = true;
    }

    public int getCost() {
        return used ? StaticValues.COST_USED_TOOLCARD : StaticValues.COST_UNUSED_TOOLCARD;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }


}
