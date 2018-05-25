package com.sagrada.ppp.model;

import java.io.Serializable;
import java.util.ArrayList;

public class JoinGameResult implements Serializable {
    private int playerHashCode;
    private int gameHashCode;
    private String username;
    private long timerStart;
    private ArrayList<String> playersUsername;

    public JoinGameResult(int playerHashCode, int gameHashCode, String username, ArrayList<String> playersUsername){
        this(playerHashCode, gameHashCode,username, 0,playersUsername );
    }
    public JoinGameResult(JoinGameResult joinGameResult){
        this.playerHashCode = joinGameResult.playerHashCode;
        this.gameHashCode = joinGameResult.gameHashCode;
        this.username = joinGameResult.username;
        this.timerStart = joinGameResult.timerStart;
        this.playersUsername = joinGameResult.playersUsername;
    }

    public JoinGameResult(int playerHashCode, int gameHashCode, String username, long timerStart, ArrayList<String> playersUsername){
        this.playerHashCode = playerHashCode;
        this.gameHashCode = gameHashCode;
        this.username = username;
        this.timerStart = timerStart;
        this.playersUsername = playersUsername;
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

    public ArrayList<String> getPlayersUsername() {
        return playersUsername;
    }

    public void setPlayersUsername(ArrayList<String> playersUsername) {
        this.playersUsername = playersUsername;
    }
}