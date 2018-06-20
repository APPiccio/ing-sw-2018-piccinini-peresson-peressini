package com.sagrada.ppp.model;

import java.io.Serializable;

/**
 * This class is the container of the player's score.
 * It calculates the total score of the player only by adding up the singles attributes
 *
 * THIS CLASS HASN'T GOT ANY GAME CALCULATION LOGIC TO AVOID APPLICATION LOGIC EXPOSITIONS
 */
public class PlayerScore implements Serializable {

    private String username;
    private int playerHashCode;
    private Color privateColor;
    private WindowPanel windowPanel;
    private int favorTokenPoints;
    private int emptyCellsPoints;
    private int privateObjectiveCardPoints;
    private int publicObjectiveCard1Points;
    private int publicObjectiveCard2Points;
    private int publicObjectiveCard3Points;
    private int totalPoints;

    PlayerScore() {
        super();
    }

    /**
     * NOTE THAT EVERY PARAM IS REFEREED TO THE SAME PLAYER
     * @param username player username
     * @param playerHashCode player ID
     * @param privateColor player's private objective color
     * @param windowPanel player's panel
     * @param favorTokenPoints remaining favor token
     * @param emptyCellsPoints panel's empty cells
     * @param privateObjectiveCardPoints
     * @param publicObjectiveCard1Points
     * @param publicObjectiveCard2Points
     * @param publicObjectiveCard3Points
     */
    public PlayerScore(String username, int playerHashCode, Color privateColor, WindowPanel windowPanel,
                       int favorTokenPoints, int emptyCellsPoints, int privateObjectiveCardPoints,
                       int publicObjectiveCard1Points, int publicObjectiveCard2Points, int publicObjectiveCard3Points) {
        this.username = username;
        this.playerHashCode = playerHashCode;
        this.privateColor = privateColor;
        this.windowPanel = windowPanel;
        this.favorTokenPoints = favorTokenPoints;
        this.emptyCellsPoints = emptyCellsPoints;
        this.privateObjectiveCardPoints = privateObjectiveCardPoints;
        this.publicObjectiveCard1Points = publicObjectiveCard1Points;
        this.publicObjectiveCard2Points = publicObjectiveCard2Points;
        this.publicObjectiveCard3Points = publicObjectiveCard3Points;
        totalPoints = favorTokenPoints + privateObjectiveCardPoints + publicObjectiveCard1Points + publicObjectiveCard2Points +
                publicObjectiveCard3Points - emptyCellsPoints;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPlayerHashCode() {
        return playerHashCode;
    }

    public void setPlayerHashCode(int playerHashCode) {
        this.playerHashCode = playerHashCode;
    }

    public Color getPrivateColor() {
        return privateColor;
    }

    void setPrivateColor(Color privateColor) {
        this.privateColor = privateColor;
    }

    public WindowPanel getWindowPanel() {
        return windowPanel;
    }

    public void setWindowPanel(WindowPanel windowPanel) {
        this.windowPanel = windowPanel;
    }

    public int getFavorTokenPoints() {
        return favorTokenPoints;
    }

    void setFavorTokenPoints(int favorTokenPoints) {
        this.favorTokenPoints = favorTokenPoints;
    }

    public int getEmptyCellsPoints() {
        return emptyCellsPoints;
    }

    void setEmptyCellsPoints(int emptyCellsPoints) {
        this.emptyCellsPoints = emptyCellsPoints;
    }

    public int getPrivateObjectiveCardPoints() {
        return privateObjectiveCardPoints;
    }

    void setPrivateObjectiveCardPoints(int privateObjectiveCardPoints) {
        this.privateObjectiveCardPoints = privateObjectiveCardPoints;
    }

    public int getPublicObjectiveCard1Points() {
        return publicObjectiveCard1Points;
    }

    void setPublicObjectiveCard1Points(int publicObjectiveCard1Points) {
        this.publicObjectiveCard1Points = publicObjectiveCard1Points;
    }

    public int getPublicObjectiveCard2Points() {
        return publicObjectiveCard2Points;
    }

    void setPublicObjectiveCard2Points(int publicObjectiveCard2Points) {
        this.publicObjectiveCard2Points = publicObjectiveCard2Points;
    }

    public int getPublicObjectiveCard3Points() {
        return publicObjectiveCard3Points;
    }

    void setPublicObjectiveCard3Points(int publicObjectiveCard3Points) {
        this.publicObjectiveCard3Points = publicObjectiveCard3Points;
    }

    public int getTotalPoints() {
        calculateTotalPoints();
        return totalPoints;
    }

    /**
     * update total score making a simple sum of each attribute
     */
    void calculateTotalPoints(){
        totalPoints =  favorTokenPoints + privateObjectiveCardPoints + publicObjectiveCard1Points +
                publicObjectiveCard2Points + publicObjectiveCard3Points - emptyCellsPoints;
    }

}