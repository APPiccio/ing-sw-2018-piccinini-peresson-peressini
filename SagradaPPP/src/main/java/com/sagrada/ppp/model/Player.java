package com.sagrada.ppp.model;

import java.io.Serializable;

/** Represents a player.
 * @author Peresson Peressini Piccinini
 */
public class Player implements Serializable {

    private String username;
    private WindowPanel panel;
    private Color privateColor;
    private PlayerStatus status;
    private Dice activeDice;
    private int favorTokens;
    private int hashCode;
    private boolean skipSecondTurn;


    /**
     * Setter for favor tokens
     */
    public void setFavorTokens(int favorTokens) {
        this.favorTokens = favorTokens;
    }

    /**
     * @return value of PlayerStatus enum ( ACTIVE: player is actively playing , INACTIVE: player is offline, afk, ecc.)
     */
    public PlayerStatus getPlayerStatus() {
        return status;
    }

    /**
     * Default setter for player status.
     * @param active value of PlayerStatus enum
     */
    public void setPlayerStatus(PlayerStatus active) {
        this.status = active;
    }


    /**
     * @param panel: panel owned by this instance of player
     */
    public void setPanel(WindowPanel panel) {
        if(this.panel == null){
            this.favorTokens = panel.getFavorTokens();
        }
        this.panel = panel;
    }

    /**
     * Generates a copy of the instance given by parameter
     * @param player: player to copy
     */
    public Player(Player player){
        this.status = player.status;
        this.username = player.username;
        this.privateColor = player.privateColor;
        this.panel = new WindowPanel(player.panel);
        this.activeDice = player.activeDice;
        this.favorTokens = player.favorTokens;
        this.hashCode = player.hashCode;
        this.skipSecondTurn = false;
    }


    /**
     * Create a player ex-novo
     * @param username: player name
     */
    public Player(String username){
        this.status = PlayerStatus.ACTIVE;
        this.panel = null;
        this.username = username;
        this.hashCode = this.hashCode();
        this.skipSecondTurn = false;

    }


    public int getFavorTokens() {
        return favorTokens;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setPrivateColor(Color color){
        this.privateColor = color;
    }

    public Color getPrivateColor() {
        return privateColor;
    }

    public WindowPanel getPanel(){return panel;}

    public int getHashCode(){
        return hashCode;
    }

    /**
     * Used for tool cards, see CommandToolCard8
     * @return true if this player has to skip his second turn.
     */
    public boolean hasToSkipSecondTurn() {
        return skipSecondTurn;
    }

    public void setSkipSecondTurn(boolean skipSecondTurn) {
        this.skipSecondTurn = skipSecondTurn;
    }
}
