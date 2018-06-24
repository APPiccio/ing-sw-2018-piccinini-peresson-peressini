package com.sagrada.ppp.network.commands;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * Class container used in socket connection. Used in order to notify players of some action (the type of the action
 * is specified by the enum
 * @see PlayerEventType
 */
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
