package com.sagrada.ppp;

import java.io.Serializable;

public class JoinGameResult implements Serializable {
    private int playerHashCode;
    private int gameHashCode;
    private String username;

    public JoinGameResult(int playerHashCode, int gameHashCode, String username){
        this.playerHashCode = playerHashCode;
        this.gameHashCode = gameHashCode;
        this.username = username;
    }

    public int getPlayerHashCode() {
        return playerHashCode;
    }

    public void setPlayerHashCode(int playerHashCode) {
        this.playerHashCode = playerHashCode;
    }

    public int getGameHashCode() {
        return gameHashCode;
    }

    public void setGameHashCode(int gameHashCode) {
        this.gameHashCode = gameHashCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
