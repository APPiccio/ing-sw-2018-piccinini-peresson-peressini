package com.sagrada.ppp.network.commands;

import java.io.Serializable;
import java.util.ArrayList;


public class PlayerEventNotification implements Response, Serializable {
    public int numOfPlayers;
    public PlayerEventType eventType;
    public String username;
    public ArrayList<String> players;

    public PlayerEventNotification(int numOfPlayers, String username, ArrayList<String> players, PlayerEventType eventType){
        this.eventType = eventType;
        this.numOfPlayers = numOfPlayers;
        this.username = username;
        this.players = players;
    }


    @Override
    public void handle(ResponseHandler handler) {
        handler.handle(this);
    }
}
