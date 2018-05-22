package com.sagrada.ppp;

import java.io.Serializable;

enum PlayerStatus {
    ACTIVE,
    INACTIVE
}

public class Player implements Serializable {

    private String username;
    private WindowPanel panel;
    private Color privateColor;
    private PlayerStatus status;
    private Dice activeDice;
    private int favorTokens;

    public void setFavorTokens(int favorTokens) {
        this.favorTokens = favorTokens;
    }

    public PlayerStatus getPlayerStatus() {
        return status;
    }

    public void setPlayerStatus(PlayerStatus active) {
        this.status = active;
    }


    public void setPanel(WindowPanel panel) {
        if(this.panel == null){
            this.favorTokens = panel.getFavorTokens();
        }
        this.panel = panel;
    }

    public Player(Player player){
        this.status = player.status;
        this.username = player.username;
        this.privateColor = player.privateColor;
        this.panel = player.panel;
        this.activeDice = player.activeDice;
        this.favorTokens = player.favorTokens;
    }
    public Player(String username){
        this.status = PlayerStatus.ACTIVE;
        this.panel = null;
        this.username = username;
    }

    public void setActiveDice(Dice activeDice) {
        this.activeDice = activeDice;
    }

    public Dice getActiveDice() {
        if(activeDice == null){
            return null;
        }
        return new Dice(activeDice);
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
}
