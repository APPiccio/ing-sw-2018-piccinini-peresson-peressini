package com.sagrada.ppp;

import java.io.Serializable;

enum PlayerStatus {
    ACTIVE,
    INACTIVE
}

public class Player implements Serializable {

    private String username;
    private String url;
    private WindowPanel panel;
    private Color privateColor;
    private PlayerStatus status;
    private Dice activeDice;


    public PlayerStatus getPlayerStatus() {
        return status;
    }

    public void setPlayerStatus(PlayerStatus active) {
        this.status = active;
    }


    public void setPanel(WindowPanel panel) {
        this.panel = panel;
    }

    public Player(Player player){
        this.status = player.status;
        this.username = player.username;
        this.privateColor = player.privateColor;
        this.activeDice = player.activeDice;
    }
    public Player(String username){
        this.status = PlayerStatus.ACTIVE;
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



    public String getUsername() {
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public Color getPrivateColor() {
        return privateColor;
    }

    public WindowPanel getPanel() {
        return new WindowPanel(panel);
    }
}
