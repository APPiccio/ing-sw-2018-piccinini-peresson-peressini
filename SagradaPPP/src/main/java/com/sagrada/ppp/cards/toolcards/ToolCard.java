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
        this.name = null;
        this.id = 0;
        this.color = null;
        this.used = false;
        this.description = null;
    }

    protected ToolCard(String name, int id, Color color) {
        this.name = name;
        this.id = id;
        this.color = color;
        this.used = false;
        this.description = StaticValues.getToolCardDescription(id);
    }

    public void use(CommandToolCard commandToolCard) {
        setUsed();
        commandToolCard.useCard();
    }

    public void setUsed() {
        used = true;
    }

    public int getId() {
        return id;
    }

    /**
     * @return 2 if the toolCard has been used at least once, 1 otherwise
     */
    public int getCost() {
        return used ? StaticValues.COST_USED_TOOL_CARD : StaticValues.COST_UNUSED_TOOL_CARD;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "ToolCard ---> Card ID: " + this.id + ", Card cost: " + this.getCost() + ", Card name: " + this.name + ", Action: " + description;
    }

}