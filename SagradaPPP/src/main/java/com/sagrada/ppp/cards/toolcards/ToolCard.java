package com.sagrada.ppp.cards.toolcards;

import com.sagrada.ppp.model.Color;
import com.sagrada.ppp.utils.StaticValues;

import java.io.Serializable;

public abstract class ToolCard implements Serializable {

    private String name;
    private int id;
    private Color color;
    private boolean used;
    private String description;

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
        this.description = StaticValues.getToolCardDescription(id);
    }

    public void use(CommandToolCard commandToolCard){
        setUsed();
        commandToolCard.useCard();
    }

    public void setUsed() {
        used = true;
    }

    public int getId() {
        return id;
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

    public String toString(){
        return "TOOLCARD ---> Card ID: " + this.id + ", Card name : " + this.name + ", Action: " + description;
    }

}