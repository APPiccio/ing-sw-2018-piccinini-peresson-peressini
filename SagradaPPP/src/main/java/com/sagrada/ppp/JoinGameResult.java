package com.sagrada.ppp;

import java.io.Serializable;

public class JoinGameResult implements Serializable {
    private int playerHashCode;
    private int gameHashCode;
    private String username;
    private long timerStart;

    public JoinGameResult(int playerHashCode, int gameHashCode, String username){
        this(playerHashCode, gameHashCode,username, 0);
    }

    public JoinGameResult(int playerHashCode, int gameHashCode, String username, long timerStart){
        this.playerHashCode = playerHashCode;
        this.gameHashCode = gameHashCode;
        this.username = username;
        this.timerStart = timerStart;
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

    public long getTimerStart() {
        return timerStart;
    }

    public void setTimerStart(long timerStart) {
        this.timerStart = timerStart;
    }
}
