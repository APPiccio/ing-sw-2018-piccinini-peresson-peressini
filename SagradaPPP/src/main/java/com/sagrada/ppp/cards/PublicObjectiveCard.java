package com.sagrada.ppp.cards;

import com.sagrada.ppp.WindowPanel;

public abstract class PublicObjectiveCard {

    private String name;
    private int id;

    public PublicObjectiveCard() {
        this.name = null;
        this.id = 0;
    }

    /**
     * @param name  name of the PublicObjectiveCard
     * @param id    number of the PublicObjectiveCard
     * @see com.sagrada.ppp.utils.StaticValues for the names of the cards
     */
    protected PublicObjectiveCard(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param playerWindowPanel windowPanel of the current player
     * @return                  number of points obtained according to the PublicObjectiveCard#
     */
    public abstract int getScore(WindowPanel playerWindowPanel);

}