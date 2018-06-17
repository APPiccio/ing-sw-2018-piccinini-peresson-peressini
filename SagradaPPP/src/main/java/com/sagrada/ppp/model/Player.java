package com.sagrada.ppp.model;

import com.sagrada.ppp.cards.toolcards.CommandToolCard8;

import java.io.Serializable;

/**
 * This class represents a player
 */
public class Player implements Serializable {

    private String username;
    private WindowPanel panel;
    private int favorTokens;
    private Color privateColor;
    private int hashCode;
    private boolean skipSecondTurn;
    private PlayerStatus status;

    /**
     * Create a new player
     * @param username player's username
     */
    public Player(String username) {
        this.username = username;
        panel = null;
        favorTokens = 0;
        privateColor = null;
        hashCode = this.hashCode();
        skipSecondTurn = false;
        status = PlayerStatus.ACTIVE;
    }

    /**
     * Generates a copy of the instance given by parameter
     * @param player player to copy
     */
    public Player(Player player) {
        this.username = player.username;
        this.panel = new WindowPanel(player.panel);
        this.favorTokens = player.favorTokens;
        this.privateColor = player.privateColor;
        this.hashCode = player.hashCode;
        this.skipSecondTurn = player.skipSecondTurn;
        this.status = player.status;
    }

    public String getUsername() {
        return username;
    }

    public WindowPanel getPanel() {
        return panel;
    }

    /**
     * @param panel panel owned by this instance of player
     */
    public void setPanel(WindowPanel panel) {
        if (this.panel == null) {
            this.favorTokens = panel.getFavorTokens();
        }
        this.panel = panel;
    }

    public int getFavorTokens() {
        return favorTokens;
    }

    /**
     * Setter for favorTokens
     */
    public void setFavorTokens(int favorTokens) {
        this.favorTokens = favorTokens;
    }

    public Color getPrivateColor() {
        return privateColor;
    }

    void setPrivateColor(Color color) {
        this.privateColor = color;
    }

    public int getHashCode() {
        return hashCode;
    }

    /**
     * Used for toolCard number 8
     * @see CommandToolCard8
     * @return true if this player has to skip his second turn
     */
    boolean hasToSkipSecondTurn() {
        return skipSecondTurn;
    }

    void setSkipSecondTurn(boolean skipSecondTurn) {
        this.skipSecondTurn = skipSecondTurn;
    }

    /**
     * @return value of PlayerStatus enum
     * (ACTIVE: player is actively playing, INACTIVE: player is offline, afk, ecc.)
     */
    PlayerStatus getPlayerStatus() {
        return status;
    }

    /**
     * Default setter for player status
     * @param active value of PlayerStatus enum
     */
    void setPlayerStatus(PlayerStatus active) {
        this.status = active;
    }
}