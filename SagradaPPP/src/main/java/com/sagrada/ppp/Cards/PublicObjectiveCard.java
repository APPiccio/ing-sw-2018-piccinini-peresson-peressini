package com.sagrada.ppp.Cards;

import com.sagrada.ppp.WindowPanel;

public abstract class PublicObjectiveCard {

    private String name;
    private int id;

    public PublicObjectiveCard() {
        this.name = null;
        this.id = 0;
    }

    protected PublicObjectiveCard(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getID() {
        return id;
    }

    public abstract int getScore(WindowPanel playerWindowPanel);

}
